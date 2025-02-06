package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.assisted;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.common.util.Constants;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.common.util.DynamoDBIndexes;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.nominee.NomineeEntity;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.product.ProductEntity;
import com.bkash.savings.models.postgres.account.AssistedRequesterType;
import com.bkash.savings.models.postgres.account.AssistedSavingsStatus;
import com.bkash.savings.models.postgres.account.SavingsType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "assisted-savings")
public class AssistedSavingsEntity implements Serializable {

    @DynamoDBRangeKey
    @DynamoDBIndexRangeKey(globalSecondaryIndexName = DynamoDBIndexes.GSI_CUSTOMER_WALLET_ID_REQUEST_ID)
    private String requestId;

    private String requesterWalletNo;

    @DynamoDBHashKey
    private String requesterWalletId;

    private String customerWalletNo;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = DynamoDBIndexes.GSI_CUSTOMER_WALLET_ID_REQUEST_ID)
    private String customerWalletId;

    @DynamoDBTypeConvertedEnum
    @DynamoDBIndexRangeKey(localSecondaryIndexName = DynamoDBIndexes.LSI_REQUESTER_WALLET_ID_SAVINGS_TYPE)
    private SavingsType savingsType;

    private ProductEntity product;

    private NomineeEntity nominee;

    @DynamoDBTypeConvertedEnum
    @DynamoDBIndexRangeKey(localSecondaryIndexName = DynamoDBIndexes.LSI_REQUESTER_WALLET_ID_STATUS)
    private AssistedSavingsStatus status;

    private String savingsAccountId;

    @DynamoDBTypeConvertedEnum
    private AssistedRequesterType assistedRequesterType;

    private String requesterName;

    @DynamoDBIndexRangeKey(localSecondaryIndexName = DynamoDBIndexes.LSI_REQUESTER_WALLET_ID_ORGANIZATION_CODE)
    private String organizationCode;

    @DynamoDBIndexRangeKey(localSecondaryIndexName = DynamoDBIndexes.LSI_REQUESTER_WALLET_ID_REQUEST_CREATE_TIME)
    private String requestCreateTime;

    private String lastUpdateTime;

    private String cycleStartDate;

    private boolean reportedByCustomer;

    private String cpsTransactionId;

    // Example Time: 2023-08-22 17:41:08:670 +0600
    private String earningReceivedDate;

    // Agent/Merchant received commission amount. Example: "50.00"
    private String commissionAmount;

    // product scheme installment amount. Ex: "500.00"
    private String productSchemeAmount;

    private String maturityDate;

    private String organizationType;

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    private boolean report;

    private String message;

    private String purpose;

    /**
     * Tenure count to send to FI for this account. Verily depends on the product term.
     */
    private String effectiveTenureCount;

    public String getEffectiveTenureCount() {
        return StringUtils.isBlank(effectiveTenureCount) ? product.getTenure() : effectiveTenureCount;
    }

    public String getPurpose() {
        return StringUtils.isEmpty(this.purpose) ? Constants.DEFAULT_PURPOSE : this.purpose;
    }

//    @DynamoDBIgnore
//    @JsonIgnore
//    public AssistedSavings toAssistedSavings() {
//        AssistedSavings assistedSavings = new AssistedSavings();
//
//        assistedSavings.setRequesterWalletNo(requesterWalletNo);
//        assistedSavings.setRequesterWalletId(requesterWalletId);
//        assistedSavings.setCustomerWalletId(customerWalletId);
//        assistedSavings.setCustomerWalletId(customerWalletId);
//        assistedSavings.setRequestId(requestId);
//        assistedSavings.setSavingsType(savingsType);
//        assistedSavings.setProduct(product.toProduct());
//        assistedSavings.setNominee(nominee.toNominee());
//        assistedSavings.setStatus(status);
//        assistedSavings.setSavingsAccountId(savingsAccountId);
//        assistedSavings.setAssistedRequesterType(assistedRequesterType);
//        assistedSavings.setRequesterName(requesterName);
//        assistedSavings.setOrganizationCode(organizationCode);
//        assistedSavings.setRequestCreateTime(requestCreateTime);
//        assistedSavings.setLastUpdatedTime(lastUpdateTime);
//        assistedSavings.setPurpose(purpose);
//        assistedSavings.setReportedByCustomer(reportedByCustomer);
//        assistedSavings.setCycleStartDate(cycleStartDate);
//        assistedSavings.setCpsTransactionId(cpsTransactionId);
//        assistedSavings.setEarningReceivedDate(earningReceivedDate);
//        assistedSavings.setCommissionAmount(commissionAmount);
//        assistedSavings.setProductSchemeAmount(productSchemeAmount);
//        return assistedSavings;
//    }
}
