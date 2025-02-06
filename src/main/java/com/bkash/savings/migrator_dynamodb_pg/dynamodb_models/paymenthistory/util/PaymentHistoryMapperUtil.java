//package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.paymenthistory.util;
//
//import com.bkash.savings.models.account.SavingsAccountEntity;
//import com.bkash.savings.models.common.util.Constants;
//import com.bkash.savings.models.common.util.DateTimeUtils;
//import com.bkash.savings.models.common.util.ObjectMapperUtils;
//import com.bkash.savings.models.payment.dto.PaymentStatus;
//import com.bkash.savings.models.paymenthistory.PaymentEntity;
//import com.bkash.savings.models.rpp.dto.PaymentWebhookRequestBody;
//
//import java.time.LocalDateTime;
//
//public class PaymentHistoryMapperUtil {
//    private PaymentHistoryMapperUtil() {}
//
//    public static PaymentEntity getPaymentHistory(SavingsAccountEntity account, long paymentId) {
//        String transactionDate = DateTimeUtils.currentTimeToDynamoDBFormat();
//
//        PaymentEntity paymentEntity = new PaymentEntity();
//        paymentEntity.setPaymentId(paymentId);
//        paymentEntity.setPaymentStatus(PaymentStatus.SUCCEEDED_PAYMENT);
//        paymentEntity.setSavingsId(account.getSavingsId());
//        paymentEntity.setFirstPayment(true);
//        paymentEntity.setAmount(account.getProduct().getAmount());
//        paymentEntity.setFiAccountNo(account.getFiAccountNo());
//        paymentEntity.setOrganizationCode(account.getOrganizationCode());
//        paymentEntity.setTrxId(account.getFirstTrxId());
//        paymentEntity.setTrxDate(transactionDate);
//        paymentEntity.setTrxShortDate(DateTimeUtils.currentTimeToShortDateFormat());
//        paymentEntity.setTimeStamp(DateTimeUtils.currentTimeToDynamoDBFormat());
//        paymentEntity.setCpsOriginatorConversationId(account.getCpsOriginatorConversationId());
//        return paymentEntity;
//    }
//
//    public static PaymentEntity getInstallmentPayHistory(SavingsAccountEntity account, String transactionId, long paymentId, String amount, String dueDate) {
//        String transactionDate = DateTimeUtils.currentTimeToDynamoDBFormat();
//
//        PaymentEntity paymentEntity = new PaymentEntity();
//        paymentEntity.setPaymentId(paymentId);
//        paymentEntity.setDueDate(dueDate);
//        paymentEntity.setPaymentStatus(PaymentStatus.SUCCEEDED_PAYMENT);
//        paymentEntity.setSavingsId(account.getSavingsId());
//        paymentEntity.setFirstPayment(false);
//        paymentEntity.setAmount(amount);
//        paymentEntity.setFiAccountNo(account.getFiAccountNo());
//        paymentEntity.setOrganizationCode(account.getOrganizationCode());
//        paymentEntity.setTrxId(transactionId);
//        paymentEntity.setTrxDate(transactionDate);
//        paymentEntity.setTrxShortDate(DateTimeUtils.currentTimeToShortDateFormat());
//        paymentEntity.setTimeStamp(DateTimeUtils.currentTimeToDynamoDBFormat());
//        paymentEntity.setCpsOriginatorConversationId(account.getCpsOriginatorConversationId());
//        paymentEntity.setInstallmentType(Constants.MISSED_PAYMENT);
//        return paymentEntity;
//    }
//
//    public static PaymentEntity getPaymentHistory(SavingsAccountEntity account, PaymentWebhookRequestBody paymentWebhookRequestBody, long paymentId, PaymentStatus paymentStatus) {
//        LocalDateTime trxDate = DateTimeUtils.convertUtcToBDTime(paymentWebhookRequestBody.getTrxDate());
//        LocalDateTime timeStamp = DateTimeUtils.convertUtcToBDTime(paymentWebhookRequestBody.getTimeStamp());
//
//        PaymentEntity paymentEntity = ObjectMapperUtils.map(paymentWebhookRequestBody, PaymentEntity.class);
//        paymentEntity.setRppSubscriptionRequestId(paymentWebhookRequestBody.getSubscriptionRequestId());
//        paymentEntity.setRppPaymentId(paymentWebhookRequestBody.getPaymentId());
//        paymentEntity.setRppSubscriptionId(paymentWebhookRequestBody.getSubscriptionId());
//        paymentEntity.setFiAccountNo(account.getFiAccountNo());
//        paymentEntity.setSavingsId(account.getSavingsId());
//        paymentEntity.setOrganizationCode(account.getOrganizationCode());
//        paymentEntity.setPaymentId(paymentId);
//        paymentEntity.setPaymentStatus(paymentStatus);
//        paymentEntity.setRppPaymentCallbackTime(DateTimeUtils.currentTimeToDynamoDBFormat());
//        paymentEntity.setTrxDate(DateTimeUtils.localDateTimeToDynamoDBFormat(trxDate));
//        paymentEntity.setTrxShortDate(paymentEntity.getTrxDate().replace("-", "").substring(0, 8));
//        paymentEntity.setTimeStamp(DateTimeUtils.localDateTimeToDynamoDBFormat(timeStamp));
//        return paymentEntity;
//    }
//}
