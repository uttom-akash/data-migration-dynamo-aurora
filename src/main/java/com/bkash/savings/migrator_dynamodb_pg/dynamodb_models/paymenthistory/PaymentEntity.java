package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.paymenthistory;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.common.util.Constants;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.common.util.DynamoDBIndexes;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.payment.dto.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "payment-history")
@Accessors(chain = true)
public class PaymentEntity {
    // savings id [primary key]
    @DynamoDBHashKey
    private String savingsId;

    // payment sequential id [sort key]
    @DynamoDBRangeKey
    private Long paymentId;

    private String amount;

    private String trxId;

    private String trxDate;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = DynamoDBIndexes.GSI_TRX_SHORT_DATE)
    private String trxShortDate;

    private String message;

    private String timeStamp;

    private String nextPaymentDate;

    private String dueDate;

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    private boolean firstPayment;

    private String rppSubscriptionRequestId;

    @DynamoDBIndexRangeKey(localSecondaryIndexName = DynamoDBIndexes.LSI_RPP_PAYMENT_ID)
    private Long rppPaymentId;

    private Long rppSubscriptionId;

    private String rppPaymentCallbackTime;

    private String fiAccountNo;

    private String organizationCode;

    @DynamoDBTypeConvertedEnum
    private PaymentStatus paymentStatus;

    private String reverseTrxId;

    private String reverseTrxDate;

    private String cpsOriginatorConversationId;

    private String installmentType;

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    private boolean refunded;

//    @DynamoDBIgnore
//    public boolean isPaymentOccurredToday() {
//        String currentDate = DateTimeUtils.currentTimeToClassicShortDateFormat();
//        return this.rppPaymentCallbackTime != null && this.rppPaymentCallbackTime.contains(currentDate)
//                && isValidPayment();
//    }

    @DynamoDBIgnore
    private boolean isValidPayment() {
        return this.paymentStatus.equals(PaymentStatus.RPP_PAYMENT_WEBHOOK_SUCCEEDED)
                || this.paymentStatus.equals(PaymentStatus.FI_PAYMENT_SEND_PENDING)
                || this.paymentStatus.equals(PaymentStatus.FI_PAYMENT_SEND_FAILED);
    }

    @DynamoDBIgnore
    public boolean isValidPayment(String organizationCode) {
        return this.organizationCode.equals(organizationCode)
                && (this.paymentStatus.equals(PaymentStatus.RPP_PAYMENT_WEBHOOK_SUCCEEDED)
                || this.paymentStatus.equals(PaymentStatus.RPP_PAYMENT_SUCCEEDED)
                || this.paymentStatus.equals(PaymentStatus.SETTLEMENT_REQUIRED)
                || this.paymentStatus.equals(PaymentStatus.SUCCEEDED_PAYMENT))
                && !this.isFirstPayment();
    }

    @DynamoDBIgnore
    public boolean isValidForInstallmentPay() {
        var status = this.getPaymentStatus();
        return Objects.nonNull(this.installmentType) && this.installmentType.equals(Constants.MISSED_PAYMENT) &&
                !(
                        status.equals(PaymentStatus.SUCCEEDED_PAYMENT) ||         // already  processed
                                status.equals(PaymentStatus.CPS_INSTALLMENT_PENDING) || // already in queue
                                status.equals(PaymentStatus.FI_INSTALLMENT_PENDING) ||
                                status.equals(PaymentStatus.FI_INSTALLMENT_FAILED) // cps ok but fi failed
                );
    }

    @DynamoDBIgnore
    public boolean isSuccessful() {
        return this.getPaymentStatus().equals(PaymentStatus.SUCCEEDED_PAYMENT) && hasTransactionId();
    }

    @DynamoDBIgnore
    public boolean isMissedPayment() {
        return Objects.nonNull(this.installmentType) && this.installmentType.equals(Constants.MISSED_PAYMENT);
    }

    @DynamoDBIgnore
    public boolean isSuccessfulMissedPayment() {
        return this.isSuccessful() && this.isMissedPayment();
    }

    @DynamoDBIgnore
    public boolean hasTransactionId() {
        return StringUtils.isNoneBlank(this.getTrxId());
    }

    @DynamoDBIgnore
    public boolean isValidAsFiBulkScheduleResponse(String organizationCode) {
        return organizationCode.equals(this.organizationCode) && !isFirstPayment() && hasTransactionId();
    }

    @DynamoDBIgnore
    public boolean isValidAsFiBulkScheduleResponseOld(String organizationCode) {
        return organizationCode.equals(this.organizationCode) && !isFirstPayment() && hasTransactionId() && !isMissedPayment();
    }

    @DynamoDBIgnore
    public boolean isInRetryQueue() {
        var status = this.getPaymentStatus();
        return Objects.nonNull(this.installmentType) && this.installmentType.equals(Constants.MISSED_PAYMENT) &&
                (
                        status.equals(PaymentStatus.CPS_INSTALLMENT_PENDING) || // already in queue
                                status.equals(PaymentStatus.FI_INSTALLMENT_PENDING) ||     // already in queue
                                status.equals(PaymentStatus.FI_INSTALLMENT_FAILED)
                );
    }

    @DynamoDBIgnore
    public boolean isValidForFiInstallment() {
        var status = this.getPaymentStatus();
        return Objects.nonNull(this.installmentType) && this.installmentType.equals(Constants.MISSED_PAYMENT) &&
                !(
                        status.equals(PaymentStatus.SUCCEEDED_PAYMENT) ||         // already  processed
                                status.equals(PaymentStatus.CPS_INSTALLMENT_PENDING) || // already in queue
                                status.equals(PaymentStatus.FI_INSTALLMENT_PENDING) ||
                                status.equals(PaymentStatus.FI_INSTALLMENT_FAILED) // cps ok but fi failed
                );
    }

    @DynamoDBIgnore
    public boolean isRppWebHookSucceeded() {
        var status = this.getPaymentStatus();
        return status == PaymentStatus.RPP_PAYMENT_WEBHOOK_SUCCEEDED;
    }
}
