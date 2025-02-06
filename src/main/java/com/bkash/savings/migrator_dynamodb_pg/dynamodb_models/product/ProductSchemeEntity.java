package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.product;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.common.util.DateTimeUtils;
import com.bkash.savings.models.postgres.account.SavingsType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument
@DynamoDBTable(tableName = "product")
@ToString
public class ProductSchemeEntity {

    // organization code
    @DynamoDBHashKey
    private String organizationCode;

    // product id
    @DynamoDBRangeKey
    private String productId;

    // product Type [LONG/SHORT Term]
    private String productType;

    // savings type [FDR/DPS]
    private String savingsType;

    // interest rate
    private String interest;

    // duration of savings -> tenure [60 Months]
    private String tenure;

    // Term weekly/monthly
    private String term;

    // amount tk
    private String amount;

    // auto renewal
    private String autoRenewal;

    // active from date
    private String activeFrom;

    // de active date
    private String deActiveFrom;

    // total money will receive
    private String totalPayout;

    // total interest earning
    private String totalSaving;

    // created date
    private Long createdDate;

    // last modified date
    private Long lastModifiedDate;

    // is created by user
    private String createByUser;

    // last modified by user
    private String lastModifiedByUser;

    private List<String> availabilityOption;

    @DynamoDBAttribute
    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    @DynamoDBAttribute
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @DynamoDBAttribute
    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @DynamoDBAttribute
    public String getInterest() {
        return (interest == null) ? "0" : interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    @DynamoDBAttribute
    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    @DynamoDBAttribute
    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @DynamoDBAttribute
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @DynamoDBAttribute
    public String getActiveFrom() {
        return activeFrom;
    }

    public void setActiveFrom(String activeFrom) {
        this.activeFrom = activeFrom;
    }

    @DynamoDBAttribute
    public String getDeActiveFrom() {
        return deActiveFrom;
    }


    public void setDeActiveFrom(String deActiveFrom) {
        this.deActiveFrom = deActiveFrom;
    }

    @DynamoDBAttribute
    public String getTotalPayout() {
        return totalPayout;
    }

    public void setTotalPayout(String totalPayout) {
        this.totalPayout = totalPayout;
    }

    @DynamoDBAttribute
    public String getTotalSaving() {
        return totalSaving;
    }

    public void setTotalSaving(String totalSaving) {
        this.totalSaving = totalSaving;
    }

    @DynamoDBAttribute
    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    @DynamoDBAttribute
    public Long getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Long lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @DynamoDBAttribute
    public String getCreateByUser() {
        return createByUser;
    }

    public void setCreateByUser(String createByUser) {
        this.createByUser = createByUser;
    }

    @DynamoDBAttribute
    public String getLastModifiedByUser() {
        return lastModifiedByUser;
    }

    public void setLastModifiedByUser(String lastModifiedByUser) {
        this.lastModifiedByUser = lastModifiedByUser;
    }

    @DynamoDBAttribute
    public String getSavingsType() {
        return StringUtils.isEmpty(this.savingsType) ? SavingsType.DPS.name() : this.savingsType;
    }

    public void setSavingsType(String savingsType) {
        this.savingsType = savingsType;
    }

    @DynamoDBAttribute
    public String getAutoRenewal() {
        return autoRenewal;
    }

    public void setAutoRenewal(String autoRenewal) {
        this.autoRenewal = autoRenewal;
    }

    @DynamoDBAttribute
    public List<String> getAvailabilityOption() {
        // Ensure the default value is an empty list if it is null
        return availabilityOption != null ? availabilityOption : Collections.emptyList();
    }

    public void setAvailabilityOption(List<String> availabilityOption) {
        this.availabilityOption = availabilityOption;
    }


    @DynamoDBIgnore
    @JsonIgnore
    public ProductScheme toProductScheme() {
        ProductScheme product = new ProductScheme();
        product.setOrganizationCode(organizationCode);
        product.setProductId(productId);
        product.setProductType(productType);
        product.setSavingsType(savingsType);
        product.setInterest(interest);
        product.setTenure(tenure);
        product.setTerm(term);
        product.setAmount(amount);
        product.setAutoRenewal(autoRenewal);
        product.setActiveFrom(activeFrom);
        product.setDeActiveFrom(deActiveFrom);
        product.setTotalPayout(totalPayout);
        product.setTotalSaving(totalSaving);
        product.setCreatedDate(createdDate);
        product.setLastModifiedDate(lastModifiedDate);
        product.setCreateByUser(createByUser);
        product.setLastModifiedByUser(lastModifiedByUser);
        product.setAvailabilityOption(availabilityOption);

        return product;
    }

    @DynamoDBIgnore
    public boolean isActive() {
        LocalDate today = LocalDate.now(DateTimeUtils.ZONE_ID_DHAKA);
        LocalDate activeFromDt = DateTimeUtils.classicShortDateToLocalDate(activeFrom).minusDays(1);

        if (today.isAfter(activeFromDt) && StringUtils.isBlank(getDeActiveFrom())) return true;

        LocalDate deactiveFromDt = DateTimeUtils.classicShortDateToLocalDate(deActiveFrom);
        return today.isAfter(activeFromDt) && today.isBefore(deactiveFromDt);
    }
}