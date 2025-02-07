package org.migration.migrators.transaction;


import org.aurora.postgres.deposit_account.DepositAccount;
import org.aurora.postgres.transaction.*;
import org.dynamo.models.paymentlog.PaymentLog;
import org.dynamo.models.paymentlog.PaymentStatus;
import org.migration.in_memory_dataset.InMemoryDepositAccountManagement;
import org.migration.mappers.DateConversion;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class TransactionProcessor implements ItemProcessor<PaymentLog, TransactionEntity> {

    private final InMemoryDepositAccountManagement inMemorySavingsAccountManagement;

    public TransactionProcessor(InMemoryDepositAccountManagement inMemorySavingsAccountManagement) {
        this.inMemorySavingsAccountManagement = inMemorySavingsAccountManagement;
    }

    @Override
    public TransactionEntity process(PaymentLog paymentHistory) throws Exception {

        var savingsAccount = inMemorySavingsAccountManagement.getSavingsAccountEntities()
                .stream()
                .filter(x -> x.getSavingsId().equals(paymentHistory.getSavingsId()))
                .findFirst()
                .orElse(null);

        if(savingsAccount == null)  return null;


        List<DepositTransaction> dps = createDpsTransaction(paymentHistory, savingsAccount);

        TransactionLog cps =  createCpsTransactionEntity(paymentHistory, savingsAccount);

        return TransactionEntity.builder()
                .dpsTransactionEntity(dps)
                .cpsTransactionEntity(cps)
                .build();
    }

    private TransactionLog createCpsTransactionEntity(PaymentLog paymentHistory, DepositAccount savingsAccount) {
        if(!doesAddCpsTrx(paymentHistory)) return null;

        var cor = UUID.randomUUID().toString();

        return TransactionLog.builder()
                    .status(getCpsTransactionStatus(paymentHistory))
                    .savingsId(paymentHistory.getSavingsId())
                    .amount(new BigDecimal(paymentHistory.getAmount()))
                    .correlationId(paymentHistory.getCpsOriginatorConversationId() == null ? cor : paymentHistory.getCpsOriginatorConversationId())
                    .trxChannel(TransactionLogChannel.DPS)
                    .trxType(getCpsTransactionType(paymentHistory))
                    .trxId(paymentHistory.getTrxId())
                    .walletNumber(savingsAccount.getWalletId())
                    .receiver(paymentHistory.getOrganizationCode())
                    .trxDate(DateConversion.toZonedDateTime(paymentHistory.getTrxDate()))
                    .trxDueDate(DateConversion.toLocalDate(paymentHistory.getDueDate()))
                    .trxSource(TransactionSource.TMS)
                    .referenceCpsTrxId(paymentHistory.getReverseTrxId())
                    .orgCode(paymentHistory.getOrganizationCode())
                    .build();
    }

    private List<DepositTransaction> createDpsTransaction(PaymentLog paymentHistory, DepositAccount savingsAccount) {

        var dpsTrxs = new ArrayList<DepositTransaction>();

        if(!doesAddOneDpsTrx(paymentHistory)) return dpsTrxs;

        dpsTrxs.add(createDpsTrx(paymentHistory, savingsAccount));

        if(!doesAddTwoDpsTrx(paymentHistory)) return dpsTrxs;

        dpsTrxs.add(CreateRefundedDpsTrx(paymentHistory,savingsAccount));

        return dpsTrxs;
    }

    private DepositTransaction createDpsTrx(PaymentLog paymentHistory, DepositAccount savingsAccount) {
        return DepositTransaction.builder()
                .savingsId(paymentHistory.getSavingsId())
                .cpsTrxId(paymentHistory.getTrxId() == null ? "" : paymentHistory.getTrxId())
                .cpsTrxDate(DateConversion.toZonedDateTime(paymentHistory.getTrxDate()))
                .amount(new BigDecimal(paymentHistory.getAmount()))
                .status(getDpsTransactionStatus(paymentHistory))
                .dueDate(DateConversion.toLocalDate(paymentHistory.getDueDate()))
                .referenceCpsConversationId(null)
                .referenceCpsTrxId(paymentHistory.getReverseTrxId())
                .referenceCpsTrxDate(DateConversion.toZonedDateTime(paymentHistory.getReverseTrxDate()))
                .type(getDpsTransactionType(paymentHistory))
                .orgCode(paymentHistory.getOrganizationCode())
                .trxSource(TransactionSource.TMS)
                .remarks(paymentHistory.getMessage())
                .build();
    }

    private DepositTransaction CreateRefundedDpsTrx(PaymentLog paymentHistory, DepositAccount savingsAccount) {
        return DepositTransaction.builder()
                .savingsId(paymentHistory.getSavingsId())
                .cpsTrxId(paymentHistory.getReverseTrxId() == null ? "" : paymentHistory.getReverseTrxId())
                .cpsTrxDate(DateConversion.toZonedDateTime(paymentHistory.getReverseTrxDate()))
                .amount(new BigDecimal(paymentHistory.getAmount()))
                .status(getSecondDpsStatus(paymentHistory))
                .dueDate(DateConversion.toLocalDate(paymentHistory.getDueDate()))
                .referenceCpsConversationId(null)
                .referenceCpsTrxId(paymentHistory.getTrxId())
                .referenceCpsTrxDate(DateConversion.toZonedDateTime(paymentHistory.getTrxDate()))
                .type(TransactionType.REFUND)
                .orgCode(paymentHistory.getOrganizationCode())
                .trxSource(TransactionSource.RPS)
                .remarks(paymentHistory.getMessage())
                .build();
    }

    private Boolean doesAddOneDpsTrx(PaymentLog item){

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

    private Boolean doesAddTwoDpsTrx(PaymentLog paymentEntity){
        var paymentStatuses = new ArrayList<PaymentStatus>();
        paymentStatuses.add(PaymentStatus.RPP_REFUND_SUCCEEDED);
        paymentStatuses.add(PaymentStatus.FI_REFUND_CONFIRMATION_FAILED);
        paymentStatuses.add(PaymentStatus.FI_REFUND_CONFIRMATION_SUCCEEDED);

        return paymentStatuses.contains(paymentEntity.getPaymentStatus());
    }

    private Boolean doesAddCpsTrx(PaymentLog item){
        var paymentStatuses = new ArrayList<PaymentStatus>();
        paymentStatuses.add(PaymentStatus.SUCCEEDED_PAYMENT);
        paymentStatuses.add(PaymentStatus.CPS_INSTALLMENT_PENDING);
        paymentStatuses.add(PaymentStatus.FI_INSTALLMENT_PENDING);
        paymentStatuses.add(PaymentStatus.FI_INSTALLMENT_FAILED);

        return paymentStatuses.contains(item.getPaymentStatus());
    }

    private TransactionLogStatus getCpsTransactionStatus(PaymentLog item){

        var successfulStatus = new ArrayList<PaymentStatus>();
        successfulStatus.add(PaymentStatus.SUCCEEDED_PAYMENT);
        successfulStatus.add(PaymentStatus.FI_INSTALLMENT_PENDING);
        successfulStatus.add(PaymentStatus.FI_INSTALLMENT_FAILED);

        if(successfulStatus.contains(item.getPaymentStatus()))
        {
            return TransactionLogStatus.SUCCESSFUL;
        }

        return TransactionLogStatus.PENDING;
    }

    private TransactionType getCpsTransactionType(PaymentLog item){
        if(item.isFirstPayment())  return  TransactionType.FIRST_DEPOSIT;
        if(item.isMissedPayment()) return  TransactionType.MISSED_DEPOSIT;

        return TransactionType.VOID;
    }

    private DepositTransactionStatus getDpsTransactionStatus(PaymentLog item){

        var pendingPaymentStatus = new ArrayList<PaymentStatus>();
        pendingPaymentStatus.add(PaymentStatus.RPP_PAYMENT_WEBHOOK_SUCCEEDED);
        pendingPaymentStatus.add(PaymentStatus.RPP_PAYMENT_SUCCEEDED);
        pendingPaymentStatus.add(PaymentStatus.SETTLEMENT_REQUIRED);
        pendingPaymentStatus.add(PaymentStatus.FI_INSTALLMENT_FAILED);

        if(pendingPaymentStatus.contains(PaymentStatus.RPP_PAYMENT_SUCCEEDED))
            return DepositTransactionStatus.PENDING;


        var unwantedPaymentStatus = new ArrayList<PaymentStatus>();
        unwantedPaymentStatus.add(PaymentStatus.RPP_REFUND_SUCCEEDED);
        unwantedPaymentStatus.add(PaymentStatus.FI_REFUND_CONFIRMATION_FAILED);
        unwantedPaymentStatus.add(PaymentStatus.FI_REFUND_CONFIRMATION_SUCCEEDED);

        if(unwantedPaymentStatus.contains(item.getPaymentStatus()))
            return DepositTransactionStatus.UNWANTED;

        if(item.getPaymentStatus().equals(PaymentStatus.SUCCEEDED_PAYMENT))
                return DepositTransactionStatus.APPROVED;

        return null;
    }

    private DepositTransactionStatus getSecondDpsStatus(PaymentLog item){

        if(item.getPaymentStatus().equals(PaymentStatus.FI_REFUND_CONFIRMATION_SUCCEEDED))
            return DepositTransactionStatus.APPROVED;

        var pendingStatuses = new ArrayList<PaymentStatus>();
        pendingStatuses.add(PaymentStatus.RPP_REFUND_SUCCEEDED);
        pendingStatuses.add(PaymentStatus.FI_REFUND_CONFIRMATION_FAILED);

        if(pendingStatuses.contains(item.getPaymentStatus()))
            return DepositTransactionStatus.PENDING;

        return null;
    }

    private TransactionType getDpsTransactionType(PaymentLog paymentEntity){

        if(paymentEntity.isFirstPayment()) return TransactionType.FIRST_DEPOSIT;
        if(paymentEntity.isMissedPayment()) return TransactionType.MISSED_DEPOSIT;

        return TransactionType.REGULAR_DEPOSIT;
    }
}