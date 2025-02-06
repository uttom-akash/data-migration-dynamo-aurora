package com.bkash.savings.migrator_dynamodb_pg.migrators.organization;

import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.organization.OrganizationEntity;
import com.bkash.savings.migrator_dynamodb_pg.mappers.DateConversion;
import com.bkash.savings.models.postgres.organization.LocalizedName;
import com.bkash.savings.models.postgres.organization.OrganizationType;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Component
public class OrganizationProcessor implements ItemProcessor<OrganizationEntity, com.bkash.savings.models.postgres.organization.OrganizationEntity> {

    @Override
    public com.bkash.savings.models.postgres.organization.OrganizationEntity process(OrganizationEntity organization) throws Exception {

        var savingsAccount = com.bkash.savings.models.postgres.organization.OrganizationEntity
                .builder()
                .organizationCode(organization.getOrganizationCode())
                .logo(organization.getLogo())
//                .localizedNames(Arrays.asList(new LocalizedName("English", "Organization Name"))) Todo: akash
                .address(organization.getAddress())
                .merchantWalletNo(organization.getMerchantWalletNo())
//                Todo: akash check
                .minSubscriptionDaysToCancelSubscription(Integer.parseInt(organization.getMinPayCyclesToCancelSubscription()))
                .merchantShortCode(organization.getMerchantShortCode() == null ? "" : organization.getMerchantShortCode())
                .organizationOperator(organization.getOrganizationOperator())
                .cpsAPIParameters(organization.getCpsAPIParameters())
                .activeInactiveDate(DateConversion.toZonedDateTime(organization.getActiveInactiveDate()))
                .organizationDomain(organization.getOrganizationDomain())
                .apiEndPoint(organization.getApiEndPoint())
                .orgDisbursementId(organization.getOrgDisbursementId())
                .privateKeyStorePassword(organization.getPrivateKeyStorePassword() == null  ? "" : organization.getPrivateKeyStorePassword())
                .privateCertAliasName(organization.getPrivateCertAliasName() == null  ? "" : organization.getPrivateCertAliasName())
                .privateCertPath(organization.getPrivateCertPath() == null  ? "" : organization.getPrivateCertPath())
                .termsAndConditionsUrl(organization.getTermsAndConditionsUrl() == null  ? "" : organization.getTermsAndConditionsUrl())
                .termsAndConditionsUrlBn(organization.getTermsAndConditionsUrlBn() == null  ? "" : organization.getTermsAndConditionsUrlBn())
                .active(true)
//               Todo : akash
               .organizationType(OrganizationType.valueOf(organization.getOrganizationType()))
                .updatedVersion(organization.isUpdatedVersion())
                .newFeatureEnabled(organization.isNewFeatureDisabled())
//               TODO: akash check
//               .v2Enabled(organization.v2)
                .build();


        var localized_names = new ArrayList<LocalizedName>();

        for (var localizedName : organization.getLocalizedNames()) {
            var name = LocalizedName.builder()
                    .language(localizedName.getLanguage())
                    .value(localizedName.getValue())
                    .organization(savingsAccount)
                    .build();

            localized_names.add(name);
        }

        savingsAccount.setLocalizedNames(localized_names); // Todo: akash check data insertion
        return savingsAccount;
    }
}






