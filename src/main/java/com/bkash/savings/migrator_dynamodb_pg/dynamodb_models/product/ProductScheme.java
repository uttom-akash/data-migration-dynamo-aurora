package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductScheme implements Serializable {
    // organization code
    private String organizationCode;

    // product id
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

    @JsonIgnore
    public ProductSchemeEntity toEntity() {
        ProductSchemeEntity product = new ProductSchemeEntity();

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
}
