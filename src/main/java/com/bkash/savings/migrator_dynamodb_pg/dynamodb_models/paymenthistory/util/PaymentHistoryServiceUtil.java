//package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.paymenthistory.util;
//
//import com.bkash.savings.models.account.SavingsAccountEntity;
//import com.bkash.savings.models.common.util.DateTimeUtils;
//import com.bkash.savings.models.payment.dto.BulkPaymentDataDTO;
//import com.bkash.savings.models.payment.dto.BulkPaymentOldDataDTO;
//import com.bkash.savings.models.payment.dto.PaymentStatus;
//import com.bkash.savings.models.rpp.dto.PaymentWebhookRequestBody;
//import com.bkash.savings.models.rpp.enums.RppWebHookResponseStatus;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//
//import java.util.List;
//
//@Slf4j
//public class PaymentHistoryServiceUtil {
//    private PaymentHistoryServiceUtil() {}
//
//    public static PaymentStatus getPaymentStatus(PaymentWebhookRequestBody paymentWebhookRequestBody, SavingsAccountEntity account) {
//        PaymentStatus paymentStatus = PaymentStatus.UNKNOWN;
//        if (paymentWebhookRequestBody.getPaymentStatus().equals(RppWebHookResponseStatus.SUCCEEDED_PAYMENT.name())) {
//            paymentStatus = PaymentStatus.RPP_PAYMENT_WEBHOOK_SUCCEEDED;
//            if (isCancellationOnSameDay(account, paymentWebhookRequestBody)) {
//                log.info("PaymentHistoryServiceUtil|getPaymentStatus::Payment trxDate {} and Savings cancellationDate {} is on same day",
//                        paymentWebhookRequestBody.getTrxDate(), account.getCancellationDate());
//                paymentStatus = PaymentStatus.SETTLEMENT_REQUIRED;
//            }
//
//        } else if (paymentWebhookRequestBody.getPaymentStatus().equals(RppWebHookResponseStatus.FAILED_PAYMENT.name())) {
//            paymentStatus = PaymentStatus.RPP_PAYMENT_WEBHOOK_FAILED;
//        }
//        return paymentStatus;
//    }
//
//    public static boolean isCancellationOnSameDay(SavingsAccountEntity account, PaymentWebhookRequestBody paymentWebhookRequestBody) {
//        if (StringUtils.isEmpty(account.getCancellationDate())) {
//            return false;
//        }
//        return DateTimeUtils.isSameDayBasedOnDynamoDBFormat(account.getCancellationDate());
//    }
//
//    public static void logPaymentData(List<BulkPaymentDataDTO> complianceData, List<BulkPaymentDataDTO> disputedData) {
//        int complianceDataSize = complianceData.size();
//        int disputedDataSize = disputedData.size();
//
//        log.info("PaymentHistoryServiceUtil|logPaymentData::Total compliance payment data found: {}", complianceDataSize);
//        log.info("PaymentHistoryServiceUtil|logPaymentData::Compliance Payment Data: {}", complianceData);
//
//        log.info("PaymentHistoryServiceUtil|logPaymentData::Total disputed payment data found: {}", disputedDataSize);
//        log.info("PaymentHistoryServiceUtil|logPaymentData::Disputed Payment Data: {}", disputedData);
//    }
//
//    public static void logPaymentDataOld(List<BulkPaymentOldDataDTO> complianceData, List<BulkPaymentOldDataDTO> disputedData) {
//        int complianceDataSize = complianceData.size();
//        int disputedDataSize = disputedData.size();
//
//        log.info("PaymentHistoryServiceUtil|logPaymentData::Total compliance payment data found: {}", complianceDataSize);
//        log.info("PaymentHistoryServiceUtil|logPaymentData::Compliance Payment Data: {}", complianceData);
//
//        log.info("PaymentHistoryServiceUtil|logPaymentData::Total disputed payment data found: {}", disputedDataSize);
//        log.info("PaymentHistoryServiceUtil|logPaymentData::Disputed Payment Data: {}", disputedData);
//    }
//}
