package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.organization;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@DynamoDBTable(tableName = "organization")
@ToString
public class OrganizationEntity {
    @DynamoDBHashKey
    private String organizationCode;

    private String logo;

    private List<LocalizedName> localizedNames;

    private String address;

    private String merchantWalletNo;

    private String minPayCyclesToCancelSubscription;

    private String merchantShortCode;

    private String organizationOperator;

    private String cpsAPIParameters;

    private String activeInactiveDate;

    private String organizationDomain;

    private String orgDisbursementId;

    private String apiEndPoint;

    private String privateKeyStorePassword;

    private String privateCertAliasName;

    private String privateCertPath;

    private String termsAndConditionsUrl;

    @Getter
    private String termsAndConditionsUrlBn;

    private boolean active = false;

    private String createdDate;

    private String lastModifiedDate;

    private String organizationType;

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    private boolean updatedVersion;

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    private boolean newFeatureDisabled;

    @DynamoDBAttribute
    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    @DynamoDBAttribute
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @DynamoDBAttribute
    public List<LocalizedName> getLocalizedNames() {
        return localizedNames;
    }

    public void setLocalizedNames(List<LocalizedName> localizedNames) {
        this.localizedNames = localizedNames;
    }

    @DynamoDBAttribute
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean isActive) {
        this.active = isActive;
    }

    @DynamoDBAttribute
    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @DynamoDBAttribute
    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }


    @DynamoDBAttribute
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @DynamoDBAttribute
    public String getMerchantWalletNo() {
        return merchantWalletNo;
    }

    public void setMerchantWalletNo(String merchantWalletNo) {
        this.merchantWalletNo = merchantWalletNo;
    }

    @DynamoDBAttribute
    public String getMerchantShortCode() {
        return merchantShortCode;
    }

    public void setMerchantShortCode(String merchantShortCode) {
        this.merchantShortCode = merchantShortCode;
    }

    @DynamoDBAttribute
    public String getOrganizationOperator() {
        return organizationOperator;
    }

    public void setOrganizationOperator(String organizationOperator) {
        this.organizationOperator = organizationOperator;
    }

    @DynamoDBAttribute
    public String getCpsAPIParameters() {
        return cpsAPIParameters;
    }

    public void setCpsAPIParameters(String cpsAPIParameters) {
        this.cpsAPIParameters = cpsAPIParameters;
    }

    @DynamoDBAttribute
    public String getActiveInactiveDate() {
        return activeInactiveDate;
    }

    public void setActiveInactiveDate(String activeInactiveDate) {
        this.activeInactiveDate = activeInactiveDate;
    }

    @DynamoDBAttribute
    public String getApiEndPoint() {
        return apiEndPoint;
    }

    public void setApiEndPoint(String apiEndPoint) {
        this.apiEndPoint = apiEndPoint;
    }

    @DynamoDBAttribute
    public String getPrivateKeyStorePassword() {
        return privateKeyStorePassword;
    }

    public void setPrivateKeyStorePassword(String privateKeyStorePassword) {
        this.privateKeyStorePassword = privateKeyStorePassword;
    }

    @DynamoDBAttribute
    public String getPrivateCertAliasName() {
        return privateCertAliasName;
    }

    public void setPrivateCertAliasName(String privateCertAliasName) {
        this.privateCertAliasName = privateCertAliasName;
    }

    @DynamoDBAttribute
    public String getPrivateCertPath() {
        return privateCertPath;
    }

    public void setPrivateCertPath(String privateCertPath) {
        this.privateCertPath = privateCertPath;
    }

    @DynamoDBAttribute
    public String getTermsAndConditionsUrl() {
        return termsAndConditionsUrl;
    }

    public void setTermsAndConditionsUrl(String termsAndConditionsUrl) {
        this.termsAndConditionsUrl = termsAndConditionsUrl;
    }

    public void setTermsAndConditionsUrlBn(String termsAndConditionsUrlBn) {
        this.termsAndConditionsUrlBn = termsAndConditionsUrlBn;
    }

    @DynamoDBAttribute
    public String getOrganizationDomain() {
        return organizationDomain;
    }

    public void setOrganizationDomain(String organizationDomain) {
        this.organizationDomain = organizationDomain;
    }

    @DynamoDBAttribute
    public String getOrgDisbursementId() {
        return orgDisbursementId;
    }

    public void setOrgDisbursementId(String orgDisbursementId) {
        this.orgDisbursementId = orgDisbursementId;
    }

    @DynamoDBAttribute
    public String getMinPayCyclesToCancelSubscription() {
        return minPayCyclesToCancelSubscription;
    }

    public void setMinPayCyclesToCancelSubscription(String minPayCyclesToCancelSubscription) {
        this.minPayCyclesToCancelSubscription = minPayCyclesToCancelSubscription;
    }

    @DynamoDBAttribute
    public String getOrganizationType() {
        return (organizationType == null) ? OrganizationType.REGULAR.name() : organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    @DynamoDBAttribute
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    public boolean isUpdatedVersion() {
        return updatedVersion;
    }

    public void setUpdatedVersion(boolean updatedVersion) {
        this.updatedVersion = updatedVersion;
    }

    public boolean isActiveByOrganizationType(String organizationType) {
        return this.active && (
                (StringUtils.isEmpty(this.organizationType) && organizationType.equalsIgnoreCase(OrganizationType.REGULAR.name()))
                        || (StringUtils.isNotEmpty(this.organizationType) && this.organizationType.equalsIgnoreCase(organizationType))
        );
    }

    @DynamoDBAttribute
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    public boolean isNewFeatureDisabled() {
        return newFeatureDisabled;
    }

    public void setNewFeatureDisabled(boolean newFeatureDisabled) {
        this.newFeatureDisabled = newFeatureDisabled;
    }

    @DynamoDBDocument
    @ToString
    public static class LocalizedName {

        private String language;
        private String value;

        @DynamoDBAttribute(attributeName = "language")
        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        @DynamoDBAttribute(attributeName = "value")
        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
