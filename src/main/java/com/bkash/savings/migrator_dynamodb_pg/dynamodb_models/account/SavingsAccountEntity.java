package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.account;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.common.util.Constants;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.common.util.DateTimeUtils;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.common.util.DynamoDBIndexes;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.nominee.SavingsNomineeEntity;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.product.ProductEntity;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.product.dto.Term;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.rpp.RppSubscriptionInfo;
import com.bkash.savings.models.postgres.account.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "account")
//@Accessors(chain = true)
public class SavingsAccountEntity {

    // Wallet number [primary key]
    @DynamoDBHashKey
    private String walletId;

    // Savings unique id [sort key] [yy-mm-dd-100100 unique id generate]
    @DynamoDBRangeKey
    private String savingsId;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = DynamoDBIndexes.GSI_WALLET_NO)
    private String walletNo;

    // Savings type [DPS/FDR/...]
    @DynamoDBTypeConvertedEnum
    private SavingsType savingsType;

    // Organization code
    private String organizationCode;

    // fi bank account id
    @DynamoDBIndexRangeKey(localSecondaryIndexName = DynamoDBIndexes.LSI_FI_ACCOUNT_ID)
    private String fiAccountId;

    // fi bank account number
    @DynamoDBIndexRangeKey(localSecondaryIndexName = DynamoDBIndexes.LSI_FI_ACCOUNT_NO)
    private String fiAccountNo;

    // SubscriptionRequestId [will be deprecated in the future and mapping with SubscriptionEntity ]
    @DynamoDBIndexHashKey(globalSecondaryIndexName = DynamoDBIndexes.GSI_SUBSCRIPTION_REQUEST_ID)
    private String subscriptionRequestId;

    // Purpose [Education]
    private String purpose;

    // status [INIT/COMPLETED/CANCELED]
    @DynamoDBTypeConvertedEnum
    private SavingsAccountStatus status;

    // savings scheme product id
    private String productId;

    // product details
    private ProductEntity product;

    // nominee details
    private SavingsNomineeEntity nominee;

    // correlation id [will be deprecated and mapping with SubscriptionEntity]
    private String correlationId;

    // current state [FI_SEND_KYC/RPP_SUBSCRIPTION_REQUEST]
    @DynamoDBTypeConvertedEnum
    @DynamoDBIndexRangeKey(globalSecondaryIndexName = DynamoDBIndexes.GSI_START_SHORT_DATE_CURRENT_STATE)
    private SavingsAccountCurrentState currentState;

    // opening date [2022-22-1-T-15-23-33] gmt+6 format
    private String openingDate;

    // savings start date
    private String startDate;

    // start short date
    @DynamoDBIndexHashKey(globalSecondaryIndexName = DynamoDBIndexes.GSI_START_SHORT_DATE_CURRENT_STATE)
    private String startShortDate;

    // savings end date
    private String endDate;

    // maturity date
    private String maturityDate;

    // maturity short date
    @DynamoDBIndexHashKey(globalSecondaryIndexName = DynamoDBIndexes.GSI_MATURITY_SHORT_DATE)
    private String maturityShortDate;

    // party name who initiates cancellation
    private String cancellationRequestedBy;

    // savings create time
    private String savingsAccountCreateTime;

    // Error reason if something happened
    private String errorReason;

    // last time it is updated
    private String savingsAccountUpdateTime;

    // rpp subscription id
//    private Long rppSubscriptionId;

    // rpp subscription request id
    @DynamoDBIndexHashKey(globalSecondaryIndexName = DynamoDBIndexes.GSI_RPP_SUBSCRIPTION_REQUEST_ID)
    private String rppSubscriptionRequestId;

    // rpp subscription callback time
    private String rppSubscriptionCallbackTime;

    // rpp cancel callback time
    private String rppCancelCallbackTime;

    // cancel request time
    private String cancelRequestTime;

    // cancel reason
    private String cancelReason;

    // cancellation
    private String cancellationDate;

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    private boolean processing = false;

    private String firstTrxId;

    private String firstTrxDate;

    private String cpsOriginatorConversationId;

    @Getter(value = AccessLevel.NONE)
    private String cycleStartDate;

    @DynamoDBTypeConvertedEnum
    private OnboardingType onboardingType;

    @DynamoDBTypeConvertedEnum
    private AssistedRequesterType assistedRequesterType;

    private String assistedRequesterWalletNumber;

    private String assistedRequesterName;

    private String assistedRequestIdRef;

    private String receivableAmount;

    private String maturityAmount;

    private String instalmentPercentage;

    private String disbursementTime;

    /**
     * Tenure count to send to FI for this account. Verily depends on the product term.
     */
    private String effectiveTenureCount;
    private List<RppSubscriptionInfo> rppSubscriptions;

    public String getEffectiveTenureCount() {
        return StringUtils.isBlank(effectiveTenureCount) ? product.getTenure() : effectiveTenureCount;
    }

    public SavingsType getSavingsType() {
        return this.savingsType == null ? SavingsType.DPS : this.savingsType;
    }

    public OnboardingType getOnboardingType() {
        return this.onboardingType == null ? OnboardingType.SELF : this.onboardingType;
    }

    public String getPurpose() {
        return StringUtils.isEmpty(this.purpose) ? Constants.DEFAULT_PURPOSE : this.purpose;
    }

    @DynamoDBIgnore
    @JsonIgnore
    public boolean isActiveOrMatured() {
        return SavingsAccountStatus.ACTIVE.equals(this.status)
                || SavingsAccountStatus.MATURED.equals(this.status);
    }

    @DynamoDBIgnore
    @JsonIgnore
    public boolean isActiveOrInit() {
        return SavingsAccountStatus.ACTIVE.equals(this.status)
                || SavingsAccountStatus.INIT.equals(this.status);
    }

    @DynamoDBIgnore
    @JsonIgnore
    public boolean isActive() {
        return SavingsAccountStatus.ACTIVE.equals(this.status);
    }

    @DynamoDBIgnore
    @JsonIgnore
    public boolean isMatured() {
        return SavingsAccountStatus.MATURED.equals(this.status);
    }

    @DynamoDBIgnore
    @JsonIgnore
    public boolean isCancelled() {
        return SavingsAccountStatus.CANCELLED.equals(this.status);
    }

    @DynamoDBIgnore
    @JsonIgnore
    public boolean isInit() {
        return SavingsAccountStatus.INIT.equals(this.status);
    }

    @DynamoDBIgnore
    @JsonIgnore
    public boolean isDisbursed() {
        return SavingsAccountStatus.DISBURSED.equals(this.status);
    }

    @DynamoDBIgnore
    @JsonIgnore
    public boolean isFailed() {
        return SavingsAccountStatus.FAILED.equals(this.status);
    }

//    @DynamoDBIgnore
//    @JsonIgnore
//    public SavingsAccount toSavingsAccount() {
//        SavingsAccount account = new SavingsAccount();
//        account.setWalletId(walletId);
//        account.setSavingsId(savingsId);
//        account.setWalletNumber(walletNo);
//        account.setSavingsType(savingsType);
//        account.setOrganizationCode(organizationCode);
//        account.setFiAccountId(fiAccountId);
//        account.setFiAccountNo(fiAccountNo);
//        account.setSubscriptionRequestId(subscriptionRequestId);
//        account.setPurpose(purpose);
//        account.setStatus(status);
//        account.setProductId(productId);
//        account.setProduct(product.toProduct());
//        account.setNominee(nominee.toNominee());
//        account.setCorrelationId(correlationId);
//        account.setCurrentState(currentState);
//        account.setOpeningDate(openingDate);
//        account.setStartDate(startDate);
//        account.setStartShortDate(startShortDate);
//        account.setEndDate(endDate);
//        account.setMaturityDate(maturityDate);
//        account.setMaturityShortDate(maturityShortDate);
//        account.setCancellationRequestedBy(cancellationRequestedBy);
//        account.setSavingsAccountCreateTime(savingsAccountCreateTime);
//        account.setErrorReason(errorReason);
//        account.setSavingsAccountUpdateTime(savingsAccountUpdateTime);
//        account.setRppSubscriptionId(rppSubscriptionId);
//        account.setRppSubscriptionRequestId(rppSubscriptionRequestId);
//        account.setRppSubscriptionCallbackTime(rppSubscriptionCallbackTime);
//        account.setRppCancelCallbackTime(rppCancelCallbackTime);
//        account.setCancelRequestTime(cancelRequestTime);
//        account.setCancelReason(cancelReason);
//        account.setCancellationDate(cancellationDate);
//        account.setProcessing(processing);
//        account.setAssistedRequesterName(assistedRequesterName);
//        account.setAssistedRequesterType(assistedRequesterType);
//        account.setOnboardingType(onboardingType);
//        account.setAssistedRequestIdRef(assistedRequestIdRef);
//        account.setAssistedRequesterWalletNumber(assistedRequesterWalletNumber);
//        account.setCpsOriginatorConversationId(cpsOriginatorConversationId);
//        account.setCycleStartDate(cycleStartDate);
//
//        return account;
//    }

    @DynamoDBIgnore
    @JsonIgnore
    public boolean isInActive() {
        return SavingsAccountStatus.INACTIVE.equals(this.status);
    }

    @DynamoDBIgnore
    @JsonIgnore
    public boolean isValidForDetailResponse() {
        return SavingsAccountStatus.ACTIVE.equals(this.status) ||
                SavingsAccountStatus.MATURED.equals(this.status) ||
                SavingsAccountStatus.CANCELLED.equals(this.status);
    }

    @JsonIgnore
    public String getCycleStartDate() {
        if (StringUtils.isNoneBlank(this.cycleStartDate)) return this.cycleStartDate;
        Term term = Term.getByDbValue(this.getProduct().getTerm());
        return DateTimeUtils.getCycleStartDate(this.startDate, term);
    }

    @JsonIgnore
    @DynamoDBIgnore
    public boolean isCycleStartDatePresentInDb() {
        return StringUtils.isNoneBlank(this.cycleStartDate);
    }

//    @DynamoDBIgnore
//    @JsonIgnore
//    public boolean isValidForManualInterpolation() {
//        switch (this.getCurrentState()) {
//            case RPP_SUBSCRIPTION_PENDING:
//            case RPP_PRE_MATURE_CANCEL_PENDING:
//            case RPP_SUBSCRIPTION_REQUEST_PENDING:
//            case RPP_SUBSCRIPTION_REQUEST_SUCCEEDED:
//            case RPP_SUBSCRIPTION_REQUEST_FAILED:
//            case FI_ACCOUNT_CREATION_PENDING:
//            case CPS_PAYMENT_SUCCEEDED:
//            case CPS_PAYMENT_PENDING:
//                return true;
//            default:
//                return false;
//        }
//    }
//
//    @DynamoDBIgnore
//    @JsonIgnore
//    public SavingsAccountEntity addRppSubscription(RppSubscriptionInfo rppSubscriptionInfo) {
//        if (Objects.isNull(rppSubscriptions) || rppSubscriptions.isEmpty()) {
//            rppSubscriptions = new ArrayList<>();
//            rppSubscriptions.add(getPrimaryRppSubscriptionInfo());
//        }
//
//        rppSubscriptions.add(rppSubscriptionInfo);
//        return this;
//    }
//
////    @DynamoDBIgnore
////    @JsonIgnore
////    private RppSubscriptionInfo getPrimaryRppSubscriptionInfo() {
////        return RppSubscriptionInfo.of(this.getRppSubscriptionRequestId(), this.getStartShortDate(),
////                this.getCycleStartDate(), this.getRppSubscriptionId());
////    }
//
//    @DynamoDBIgnore
//    @JsonIgnore
//    public RppSubscriptionInfo getLatestRppSubscriptionInfo() {
//        return Objects.isNull(rppSubscriptions) || rppSubscriptions.isEmpty() ?
//                getPrimaryRppSubscriptionInfo() : rppSubscriptions.get(rppSubscriptions.size() - 1);
//    }
//
//    @DynamoDBIgnore
//    @JsonIgnore
//    public boolean hasMultipleRppSubscriptions() {
//        return Objects.nonNull(rppSubscriptions) && rppSubscriptions.size() > 1;
//    }
//
//    @DynamoDBIgnore
//    @JsonIgnore
//    public boolean isWeekly() {
//        return getProduct().getTerm().equals(Term.WEEKLY.toString());
//    }
//
//    @DynamoDBIgnore
//    @JsonIgnore
//    public boolean isSameMturityAndEndDate() {
//        var eDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern(DateTimeUtils.DYNAMO_DB_DATE_FORMAT));
//        String endShortDate = eDate.format(DateTimeFormatter.ofPattern(DateTimeUtils.SHORT_DATE_FORMAT));
//        return maturityShortDate.equals(endShortDate);
//    }
}
