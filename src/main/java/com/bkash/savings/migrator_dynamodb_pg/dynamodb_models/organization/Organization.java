package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.organization;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Organization {
    private String organizationCode;

    private String logo;

    private List<OrganizationEntity.LocalizedName> localizedNames;

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

    private boolean active = false;

    private String createdDate;

    private String lastModifiedDate;

    private String organizationType;

    private boolean updatedVersion;

    private boolean newFeatureDisabled;

}
