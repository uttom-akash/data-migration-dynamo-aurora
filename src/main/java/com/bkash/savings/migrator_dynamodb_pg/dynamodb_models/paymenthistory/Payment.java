//package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.paymenthistory;
//
//import com.bkash.savings.models.common.util.DateTimeUtils;
//import com.bkash.savings.models.payment.dto.PaymentStatus;
//import lombok.*;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//public class Payment {
//    // savings account id
//    private String savingsAccountId;
//
//    // payment sequential id
//    private Long paymentId;
//
//    private String amount;
//
//    private String trxId;
//
//    private String trxDate;
//
//    private String trxShortDate;
//
//    private String message;
//
//    private String timeStamp;
//
//    private String nextPaymentDate;
//
//    private String dueDate;
//
//    private boolean firstPayment;
//
//    private String rppSubscriptionRequestId;
//
//    private Long rppPaymentId;
//
//    private Long rppSubscriptionId;
//
//    private String rppPaymentCallbackTime;
//
//    private String fiAccountNo;
//
//    private String organizationCode;
//
//    private PaymentStatus paymentStatus;
//
//    private String reverseTrxId;
//
//    private String reverseTrxDate;
//
//    private String cpsOriginatorConversationId;
//
//    public boolean isPaymentOccurredToday() {
//        String currentDate = DateTimeUtils.currentTimeToClassicShortDateFormat();
//        return this.rppPaymentCallbackTime != null && this.rppPaymentCallbackTime.contains(currentDate)
//                && isValidPayment();
//    }
//
//    private boolean isValidPayment() {
//        return this.paymentStatus.equals(PaymentStatus.RPP_PAYMENT_WEBHOOK_SUCCEEDED)
//                || this.paymentStatus.equals(PaymentStatus.FI_PAYMENT_SEND_PENDING)
//                || this.paymentStatus.equals(PaymentStatus.FI_PAYMENT_SEND_FAILED);
//    }
//
//    public boolean isValidPayment(String organizationCode) {
//        return this.organizationCode.equals(organizationCode)
//                && (this.paymentStatus.equals(PaymentStatus.RPP_PAYMENT_WEBHOOK_SUCCEEDED)
//                || this.paymentStatus.equals(PaymentStatus.RPP_PAYMENT_SUCCEEDED)
//                || this.paymentStatus.equals(PaymentStatus.SETTLEMENT_REQUIRED))
//                && !this.firstPayment;
//    }
//
//
//}
