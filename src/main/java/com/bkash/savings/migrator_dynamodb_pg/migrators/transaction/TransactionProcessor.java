package com.bkash.savings.migrator_dynamodb_pg.migrators.transaction;

import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.payment.dto.PaymentStatus;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.paymenthistory.PaymentEntity;
import com.bkash.savings.migrator_dynamodb_pg.in_memory_data.InMemoryProductManagement;
import com.bkash.savings.migrator_dynamodb_pg.in_memory_data.InMemorySavingsAccountManagement;
import com.bkash.savings.migrator_dynamodb_pg.mappers.DateConversion;
import com.bkash.savings.models.dto.dps.DpsPlanNumberRequest;
import com.bkash.savings.models.dto.fi.FiTransactionstatus;
import com.bkash.savings.models.postgres.account.SavingsAccountEntity;
import com.bkash.savings.models.postgres.transaction.*;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class TransactionProcessor implements ItemProcessor<PaymentEntity, TransactionEntity> {

    private final InMemorySavingsAccountManagement inMemorySavingsAccountManagement;
    private final InMemoryProductManagement inMemoryProductManagement;
    private final DpsPlanGenerator dpsPlanGenerator;

    public TransactionProcessor(InMemorySavingsAccountManagement inMemorySavingsAccountManagement, InMemoryProductManagement inMemoryProductManagement,
                                DpsPlanGenerator dpsPlanGenerator

    ) {
        this.inMemorySavingsAccountManagement = inMemorySavingsAccountManagement;
        this.inMemoryProductManagement = inMemoryProductManagement;
        this.dpsPlanGenerator = dpsPlanGenerator;
    }

    @Override
    public TransactionEntity process(PaymentEntity paymentHistory) throws Exception {

        var savingsAccount = inMemorySavingsAccountManagement.getSavingsAccountEntities()
                .stream()
                .filter(x -> x.getSavingsId().equals(paymentHistory.getSavingsId()))
                .findFirst()
                .orElse(null);

        if(savingsAccount == null)  return null;

        var product = inMemoryProductManagement.getProductEntities()
                .stream()
                .filter(x->x.getProductId().equals(savingsAccount.getProductCode()))
                .findFirst()
                .orElse(null);

        if(product == null) return null;

        var dpsPlanNumberRequest = DpsPlanNumberRequest
                .builder()
                .startDate(DateConversion.toLocalDate(savingsAccount.getStartDate().toString()))
                .cycleStartDate(savingsAccount.getCycleStartDate())
                .term(product.getTerm())// Todo-akash : use product
                .tenure(savingsAccount.getEffectiveTenureCount())
                .trxDate(DateConversion.toLocalDate(paymentHistory.getTrxDate()))
                .dueDate(DateConversion.toLocalDate(paymentHistory.getDueDate()))
                .build();



        int bkPlanNo = 0;

        try {
            bkPlanNo = dpsPlanGenerator
                    .getPlanNumber(dpsPlanNumberRequest)
                    .planNo();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        List<DpsTransactionEntity> dps = createDpsTransaction(paymentHistory, bkPlanNo, savingsAccount);

        CpsTransactionEntity cps =  createCpsTransactionEntity(paymentHistory, savingsAccount);

        return TransactionEntity.builder()
                .dpsTransactionEntity(dps)
                .cpsTransactionEntity(cps)
                .build();
    }

    private CpsTransactionEntity createCpsTransactionEntity(PaymentEntity paymentHistory, SavingsAccountEntity savingsAccount) {
        if(!doesAddCpsTrx(paymentHistory)) return null;

        var cor = UUID.randomUUID().toString();

        return CpsTransactionEntity.builder()
                    .status(getCpsTransactionStatus(paymentHistory))
                    .savingsId(paymentHistory.getSavingsId())
                    .amount(new BigDecimal(paymentHistory.getAmount()))
                    .originatorConversationId(paymentHistory.getCpsOriginatorConversationId() == null ? cor : paymentHistory.getCpsOriginatorConversationId() )
                    .correlationId(cor)
                    .trxChannel(CpsTransactionChannel.DPS)
                    .trxType(getCpsTransactionType(paymentHistory))
                    .trxId(paymentHistory.getTrxId())
                    .fiAccountId(savingsAccount.getFiAccountId() == null ? "": savingsAccount.getFiAccountId())
                    .fiAccountNumber(paymentHistory.getFiAccountNo())
                    .walletNumber(savingsAccount.getWalletId())
                    .receiver(paymentHistory.getOrganizationCode())
                    .trxDate(DateConversion.toZonedDateTime(paymentHistory.getTrxDate()))
                    .trxDueDate(DateConversion.toLocalDate(paymentHistory.getDueDate()))
                    .request_initiated_time(DateConversion.toZonedDateTime(paymentHistory.getTrxDate()))
                    .trxSource(TransactionSource.TMS)
                    .referenceCpsTrxId(paymentHistory.getReverseTrxId())
                    .orgCode(paymentHistory.getOrganizationCode())
                    .build();
    }

    private List<DpsTransactionEntity> createDpsTransaction(PaymentEntity paymentHistory, int bkPlanNo, SavingsAccountEntity savingsAccount) {

        var dpsTrxs = new ArrayList<DpsTransactionEntity>();

        if(!doesAddOneDpsTrx(paymentHistory)) return dpsTrxs;

        dpsTrxs.add(createDpsTrx(paymentHistory, bkPlanNo, savingsAccount));

        if(!doesAddTwoDpsTrx(paymentHistory)) return dpsTrxs;

        dpsTrxs.add(CreateRefundedDpsTrx(paymentHistory, bkPlanNo,savingsAccount));

        return dpsTrxs;
    }

    private DpsTransactionEntity createDpsTrx(PaymentEntity paymentHistory, int bkPlanNo, SavingsAccountEntity savingsAccount) {
        return DpsTransactionEntity.builder()
                .savingsId(paymentHistory.getSavingsId())
                .cpsTrxId(paymentHistory.getTrxId() == null ? "" : paymentHistory.getTrxId())
                .cpsTrxDate(DateConversion.toZonedDateTime(paymentHistory.getTrxDate()))
                .amount(new BigDecimal(paymentHistory.getAmount()))
                .status(getDpsTransactionStatus(paymentHistory))
                .dueDate(DateConversion.toLocalDate(paymentHistory.getDueDate()))
                .bkPlanNo(bkPlanNo)
                .fiPlanNo(null)
//                  .fiTrxId(paymentHistory.getFiTrxId())
//                  .fiTrxDate(toZonedDateTime(paymentHistory.getFiTrxDate()))
                .fiAccountId(savingsAccount.getFiAccountId() == null ? "" : savingsAccount.getFiAccountId())
                .fiAccountNumber(paymentHistory.getFiAccountNo())
                .referenceCpsConversationId(null)
                .referenceCpsTrxId(paymentHistory.getReverseTrxId())
                .referenceCpsTrxDate(DateConversion.toZonedDateTime(paymentHistory.getReverseTrxDate()))
                .type(getDpsTransactionType(paymentHistory))
                .orgCode(paymentHistory.getOrganizationCode())
                .trxSource(TransactionSource.TMS)
                .rppPaymentId(paymentHistory.getRppPaymentId())
                .remarks(paymentHistory.getMessage())
                .fiStatus(FiTransactionstatus.UNKNOWN)
                .build();
    }

    private DpsTransactionEntity CreateRefundedDpsTrx(PaymentEntity paymentHistory, int bkPlanNo, SavingsAccountEntity savingsAccount) {
        return DpsTransactionEntity.builder()
                .savingsId(paymentHistory.getSavingsId())
                .cpsTrxId(paymentHistory.getReverseTrxId() == null ? "" : paymentHistory.getReverseTrxId())
                .cpsTrxDate(DateConversion.toZonedDateTime(paymentHistory.getReverseTrxDate()))
                .amount(new BigDecimal(paymentHistory.getAmount()))
                .status(getSecondDpsStatus(paymentHistory))
                .dueDate(DateConversion.toLocalDate(paymentHistory.getDueDate()))
                .bkPlanNo(bkPlanNo)
                .fiPlanNo(null)
                .fiAccountId(savingsAccount.getFiAccountId() == null ? "" : savingsAccount.getFiAccountId())
                .fiAccountNumber(savingsAccount.getFiAccountNo())
                .referenceCpsConversationId(null)
                .referenceCpsTrxId(paymentHistory.getTrxId())
                .referenceCpsTrxDate(DateConversion.toZonedDateTime(paymentHistory.getTrxDate()))
                .type(TransactionType.REFUND)
                .orgCode(paymentHistory.getOrganizationCode())
                .trxSource(TransactionSource.RPS)
                .rppPaymentId(paymentHistory.getRppPaymentId())
                .remarks(paymentHistory.getMessage())
                .fiStatus(FiTransactionstatus.UNKNOWN)
                .build();
    }


//    private TransactionType getTransactionType(PaymentEntity item){
//
//        if(item.isFirstPayment()) return TransactionType.FIRST_DEPOSIT;
//        if(item.getInstallmentType() !=null && item.getInstallmentType().equals("MissedPayment")) return TransactionType.MISSED_DEPOSIT;
//
//        return  TransactionType.REGULAR_DEPOSIT;
//    }

    private Boolean doesAddOneDpsTrx(PaymentEntity item){

        var paymentStatuses = new ArrayList<PaymentStatus>();
        paymentStatuses.add(PaymentStatus.SUCCEEDED_PAYMENT);
        paymentStatuses.add(PaymentStatus.CPS_INSTALLMENT_PENDING);
        paymentStatuses.add(PaymentStatus.FI_INSTALLMENT_PENDING);
        paymentStatuses.add(PaymentStatus.FI_INSTALLMENT_FAILED);
        paymentStatuses.add(PaymentStatus.SETTLEMENT_REQUIRED);
        paymentStatuses.add(PaymentStatus.RPP_PAYMENT_SUCCEEDED);
        paymentStatuses.add(PaymentStatus.RPP_PAYMENT_WEBHOOK_SUCCEEDED);

        return paymentStatuses.contains(item.getPaymentStatus());
    }

    private Boolean doesAddTwoDpsTrx(PaymentEntity paymentEntity){
        var paymentStatuses = new ArrayList<PaymentStatus>();
        paymentStatuses.add(PaymentStatus.RPP_REFUND_SUCCEEDED);
        paymentStatuses.add(PaymentStatus.FI_REFUND_CONFIRMATION_FAILED);
        paymentStatuses.add(PaymentStatus.FI_REFUND_CONFIRMATION_SUCCEEDED);

        return paymentStatuses.contains(paymentEntity.getPaymentStatus());
    }

    private Boolean doesAddCpsTrx(PaymentEntity item){
        var paymentStatuses = new ArrayList<PaymentStatus>();
        paymentStatuses.add(PaymentStatus.SUCCEEDED_PAYMENT);
        paymentStatuses.add(PaymentStatus.CPS_INSTALLMENT_PENDING);
        paymentStatuses.add(PaymentStatus.FI_INSTALLMENT_PENDING);
        paymentStatuses.add(PaymentStatus.FI_INSTALLMENT_FAILED);

        return paymentStatuses.contains(item.getPaymentStatus());
    }

    private CpsTransactionStatus getCpsTransactionStatus(PaymentEntity item){

        var successfulStatus = new ArrayList<PaymentStatus>();
        successfulStatus.add(PaymentStatus.SUCCEEDED_PAYMENT);
        successfulStatus.add(PaymentStatus.FI_INSTALLMENT_PENDING);
        successfulStatus.add(PaymentStatus.FI_INSTALLMENT_FAILED);

        if(successfulStatus.contains(item.getPaymentStatus()))
        {
            return CpsTransactionStatus.SUCCESSFUL;
        }

        return CpsTransactionStatus.PENDING;
    }

    private TransactionType getCpsTransactionType(PaymentEntity item){
        if(item.isFirstPayment())  return  TransactionType.FIRST_DEPOSIT;
        if(item.isMissedPayment()) return  TransactionType.MISSED_DEPOSIT;

        return TransactionType.VOID;
    }

    private DpsTransactionStatus getDpsTransactionStatus(PaymentEntity item){

        var pendingPaymentStatus = new ArrayList<PaymentStatus>();
        pendingPaymentStatus.add(PaymentStatus.RPP_PAYMENT_WEBHOOK_SUCCEEDED);
        pendingPaymentStatus.add(PaymentStatus.RPP_PAYMENT_SUCCEEDED);
        pendingPaymentStatus.add(PaymentStatus.SETTLEMENT_REQUIRED);
        pendingPaymentStatus.add(PaymentStatus.FI_INSTALLMENT_FAILED);

        if(pendingPaymentStatus.contains(PaymentStatus.RPP_PAYMENT_SUCCEEDED))
            return DpsTransactionStatus.PENDING;


        var unwantedPaymentStatus = new ArrayList<PaymentStatus>();
        unwantedPaymentStatus.add(PaymentStatus.RPP_REFUND_SUCCEEDED);
        unwantedPaymentStatus.add(PaymentStatus.FI_REFUND_CONFIRMATION_FAILED);
        unwantedPaymentStatus.add(PaymentStatus.FI_REFUND_CONFIRMATION_SUCCEEDED);

        if(unwantedPaymentStatus.contains(item.getPaymentStatus()))
            return DpsTransactionStatus.UNWANTED;

        if(item.getPaymentStatus().equals(PaymentStatus.SUCCEEDED_PAYMENT))
                return DpsTransactionStatus.APPROVED;

        return null;
    }

    private DpsTransactionStatus getSecondDpsStatus(PaymentEntity item){

        if(item.getPaymentStatus().equals(PaymentStatus.FI_REFUND_CONFIRMATION_SUCCEEDED))
            return DpsTransactionStatus.APPROVED;

        var pendingStatuses = new ArrayList<PaymentStatus>();
        pendingStatuses.add(PaymentStatus.RPP_REFUND_SUCCEEDED);
        pendingStatuses.add(PaymentStatus.FI_REFUND_CONFIRMATION_FAILED);

        if(pendingStatuses.contains(item.getPaymentStatus()))
            return DpsTransactionStatus.PENDING;

        return null;
    }

    private TransactionType getDpsTransactionType(PaymentEntity paymentEntity){

        if(paymentEntity.isFirstPayment()) return TransactionType.FIRST_DEPOSIT;
        if(paymentEntity.isMissedPayment()) return TransactionType.MISSED_DEPOSIT;

        return TransactionType.REGULAR_DEPOSIT;
    }
}