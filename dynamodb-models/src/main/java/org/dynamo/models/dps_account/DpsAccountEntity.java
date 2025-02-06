package org.dynamo.models.dps_account;


import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;
import org.dynamo.models.DynamoDBIndexes;
import org.dynamo.models.dps_nominee.DpsNominee;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "account")
public class DpsAccountEntity {
    @DynamoDBHashKey
    private String walletId;

    @DynamoDBRangeKey
    private String savingsId;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = DynamoDBIndexes.GSI_WALLET_NO)
    private String walletNo;

    @DynamoDBTypeConvertedEnum
    private DpsType savingsType;

    private String organizationCode;

    @DynamoDBIndexRangeKey(localSecondaryIndexName = DynamoDBIndexes.LSI_FI_ACCOUNT_ID)
    private String fiAccountId;

    @DynamoDBIndexRangeKey(localSecondaryIndexName = DynamoDBIndexes.LSI_FI_ACCOUNT_NO)
    private String fiAccountNo;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = DynamoDBIndexes.GSI_SUBSCRIPTION_REQUEST_ID)
    private String subscriptionRequestId;

    private String purpose;

    @DynamoDBTypeConvertedEnum
    private DpsAccountStatus status;

    private String productCode;

    private DpsNominee nominee;

    private String correlationId;

    @DynamoDBTypeConvertedEnum
    private DpsAccountCurrentState currentState;

    private String openingDate;

    private String startDate;

    private String startShortDate;

    private String endDate;

    private String maturityDate;

    private String maturityShortDate;

    private String cancellationRequestedBy;

    private String savingsAccountCreateTime;

    private String errorReason;

    private String savingsAccountUpdateTime;

    private String rppSubscriptionRequestId;

    private String rppSubscriptionCallbackTime;

    private String rppCancelCallbackTime;

    private String cancelRequestTime;

    private String cancelReason;

    private String cancellationDate;

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    private boolean processing = false;

    private String firstTrxId;

    private String firstTrxDate;

    private String cpsOriginatorConversationId;

    private String cycleStartDate;

    private String assistedRequesterWalletNumber;

    private String assistedRequesterName;

    private String assistedRequestIdRef;

    private String receivableAmount;

    private String maturityAmount;

    private String instalmentPercentage;

    private String disbursementTime;
}
