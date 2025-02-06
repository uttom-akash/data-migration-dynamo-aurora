package org.dynamo.models.paymentlog;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.dynamo.models.DynamoDBIndexes;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "payment-history")
@Accessors(chain = true)
public class PaymentLog {
    @DynamoDBHashKey
    private String savingsId;

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

    @DynamoDBIgnore
    public boolean isMissedPayment() {
        return Objects.nonNull(this.installmentType) && this.installmentType.equals("MISSED_PAYMENT");
    }
}
