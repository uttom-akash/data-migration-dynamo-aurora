package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.payment;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.common.util.DynamoDBIndexesOld;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.payment.dto.PaymentStatus;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.format.DateTimeFormatter;

@Slf4j
@DynamoDBTable(tableName = "payment")
@ToString
public class PaymentEntity {

    @DynamoDBIgnore
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private long subscriptionId;
    private String subscriptionRequestId;
    private String message;
    private String timeStamp;
    private String nextPaymentDate;
    private String trxId;
    private String amount;
    private String trxDate;
    private String trxShortDate;
    private long paymentId;
    private String dueDate;
    private boolean firstPayment;
    private String accountNo;
    private String organizationCode;
    private String status;
    private String rppPaymentCallbackTime;
    private String fiRequestTime;
    private String reverseTrxId;
    private String reverseTrxDate;
    private String schedulePaymentErrorReason;

    @DynamoDBHashKey(attributeName = "subscriptionRequestId")
    public String getSubscriptionRequestId() {
        return subscriptionRequestId;
    }

    public void setSubscriptionRequestId(String subscriptionRequestId) {
        this.subscriptionRequestId = subscriptionRequestId;
    }

    @DynamoDBAttribute(attributeName = "timeStamp")
    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @DynamoDBAttribute(attributeName = "firstPayment")
    public boolean isFirstPayment() {
        return firstPayment;
    }

    public void setFirstPayment(boolean firstPayment) {
        this.firstPayment = firstPayment;
    }

    @DynamoDBRangeKey
    @DynamoDBAttribute(attributeName = "paymentId")
    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    @DynamoDBAttribute(attributeName = "subscriptionId")
    public long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    @DynamoDBAttribute(attributeName = "trxId")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = DynamoDBIndexesOld.INDEX_TRX_ID)
    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    @DynamoDBAttribute(attributeName = "amount")
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @DynamoDBAttribute(attributeName = "dueDate")
    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @DynamoDBAttribute(attributeName = "nextPaymentDate")
    public String getNextPaymentDate() {
        return nextPaymentDate;
    }

    public void setNextPaymentDate(String nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
    }

    @DynamoDBAttribute(attributeName = "trxDate")
    public String getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(String trxDate) {
        this.trxDate = trxDate;
    }

    @DynamoDBAttribute(attributeName = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @DynamoDBAttribute(attributeName = "accountNo")
    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    @DynamoDBAttribute
    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    @DynamoDBAttribute(attributeName = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @DynamoDBAttribute(attributeName = "rppPaymentCallbackTime")
    public String getRppPaymentCallbackTime() {
        return rppPaymentCallbackTime;
    }

    public void setRppPaymentCallbackTime(String rppPaymentCallbackTime) {
        this.rppPaymentCallbackTime = rppPaymentCallbackTime;
    }

    @DynamoDBAttribute(attributeName = "schedulePaymentErrorReason")
    public String getSchedulePaymentErrorReason() {
        return schedulePaymentErrorReason;
    }

    public void setSchedulePaymentErrorReason(String schedulePaymentErrorReason) {
        this.schedulePaymentErrorReason = schedulePaymentErrorReason;
    }

    @DynamoDBAttribute(attributeName = "fiRequestTime")
    public String getFiRequestTime() {
        return fiRequestTime;
    }

    public void setFiRequestTime(String fiRequestTime) {
        this.fiRequestTime = fiRequestTime;
    }

    @DynamoDBIgnore
    public boolean isNotSyncedPaymentExceptFirstPayment() {
        return isValidSchedulePayment()
                && !isFirstPayment();
    }

    @DynamoDBIgnore
    private boolean isValidSchedulePayment() {
        return this.status.equals(PaymentStatus.RPP_PAYMENT_WEBHOOK_SUCCEEDED.name())
                || this.status.equals(PaymentStatus.FI_PAYMENT_SEND_PENDING.name());
    }

    @DynamoDBIgnore
    private boolean isValidPayment() {
        return this.status.equals(PaymentStatus.RPP_PAYMENT_WEBHOOK_SUCCEEDED.name())
                || this.status.equals(PaymentStatus.FI_PAYMENT_SEND_PENDING.name())
                || this.status.equals(PaymentStatus.FI_PAYMENT_SEND_FAILED.name());
    }

    @DynamoDBIgnore
    public boolean isValidPayment(String organizationCode) {
        return this.organizationCode.equals(organizationCode)
                && (this.status.equals(PaymentStatus.RPP_PAYMENT_WEBHOOK_SUCCEEDED.name())
                || this.status.equals(PaymentStatus.SETTLEMENT_REQUIRED.name()))
                && !isFirstPayment();
    }


    @DynamoDBAttribute
    public String getReverseTrxId() {
        return reverseTrxId;
    }

    public void setReverseTrxId(String reverseTrxId) {
        this.reverseTrxId = reverseTrxId;
    }

    @DynamoDBAttribute
    public String getReverseTrxDate() {
        return reverseTrxDate;
    }

    public void setReverseTrxDate(String reverseTrxDate) {
        this.reverseTrxDate = reverseTrxDate;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = DynamoDBIndexesOld.INDEX_TRX_SHORT_DATE)
    public String getTrxShortDate() {
        return trxShortDate;
    }

    public void setTrxShortDate(String trxShortDate) {
        this.trxShortDate = trxShortDate;
    }
//
//    public boolean equalHistory(com.bkash.savings.models.paymenthistory.PaymentEntity paymentHistory) {
//        String paymentString = this.getSubscriptionId()
//                + this.getSubscriptionRequestId()
//                + this.getMessage()
//                + this.getTimeStamp()
//                + this.getNextPaymentDate()
//                + this.getTrxId()
//                + this.getAmount()
//                + this.getTrxDate()
//                + this.getTrxShortDate()
//                + this.getPaymentId()
//                + this.getDueDate()
//                + this.isFirstPayment()
//                + this.getAccountNo()
//                + this.getOrganizationCode()
//                + this.getStatus()
//                + this.getRppPaymentCallbackTime()
//                + this.getReverseTrxId()
//                + this.getReverseTrxDate();
//
//        String paymentHistoryString = paymentHistory.getRppSubscriptionId()
//                + paymentHistory.getRppSubscriptionRequestId()
//                + paymentHistory.getMessage()
//                + paymentHistory.getTimeStamp()
//                + paymentHistory.getNextPaymentDate()
//                + paymentHistory.getTrxId()
//                + paymentHistory.getAmount()
//                + paymentHistory.getTrxDate()
//                + paymentHistory.getTrxShortDate()
//                + paymentHistory.getRppPaymentId()
//                + paymentHistory.getDueDate()
//                + paymentHistory.isFirstPayment()
//                + paymentHistory.getFiAccountNo()
//                + paymentHistory.getOrganizationCode()
//                + paymentHistory.getPaymentStatus()
//                + paymentHistory.getRppPaymentCallbackTime()
//                + paymentHistory.getReverseTrxId()
//                + paymentHistory.getReverseTrxDate();
//
//        return paymentHistoryString.equals(paymentString);
//    }
}