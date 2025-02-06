package com.bkash.savings.models.postgres.organization;

import com.bkash.savings.models.postgres.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.SqlTypes;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "organization", uniqueConstraints = {
        @UniqueConstraint(name = "uc_organization_org_disbursement_id", columnNames = {"org_disbursement_id"})
})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE,
        region = "entity.organization")
public class OrganizationEntity extends BaseEntity {
    @Column(name = "organization_code", nullable = false, unique = true, length = 20)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String organizationCode;

    @Column(name = "logo", columnDefinition = "TEXT")
    private String logo;

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE,
            region = "entity.organization.localizedNames")
    @Builder.Default
    @OneToMany(mappedBy = "organization", orphanRemoval = true, fetch = FetchType.EAGER) // changed to LAZY
    private List<LocalizedName> localizedNames = new ArrayList<>();

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "merchant_wallet_no", nullable = false, length = 20)
    private String merchantWalletNo;

    // previous column name: "min_pay_days_to_cancellation"
    // this does not reflect the value set in existing prod database
    // in frontend, we are expecting a cycle
    @Column(name = "min_subscription_days_to_cancellation", nullable = false)
    @JdbcTypeCode(SqlTypes.SMALLINT)
    private Integer minSubscriptionDaysToCancelSubscription;

    @Column(name = "merchant_short_code", nullable = false, length = 20)
    private String merchantShortCode;

    @Column(name = "organization_operator", nullable = false)
    private String organizationOperator;

    @Column(name = "cps_api_parameters")
    private String cpsAPIParameters;

    @Column(name = "active_inactive_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime activeInactiveDate;

    @Column(name = "organization_domain", nullable = false)
    private String organizationDomain;

    @Column(name = "api_end_point", nullable = false)
    private String apiEndPoint;

    @Column(name = "org_disbursement_id", nullable = false)
    private String orgDisbursementId;

    @Column(name = "private_key_store_password", nullable = false)
    private String privateKeyStorePassword;

    @Column(name = "private_cert_alias_name", nullable = false)
    private String privateCertAliasName;

    @Column(name = "private_cert_path", nullable = false)
    private String privateCertPath;

    @Column(name = "terms_and_conditions_url", nullable = false, columnDefinition = "TEXT")
    private String termsAndConditionsUrl;

    @Column(name = "terms_and_conditions_url_bn", nullable = false, columnDefinition = "TEXT")
    private String termsAndConditionsUrlBn;

    @Builder.Default
    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "organization_type", nullable = false, length = 20)
    private OrganizationType organizationType;

    @Column(name = "updated_version")
    private Boolean updatedVersion;

    @Column(name = "new_feature_enabled", nullable = false)
    @Builder.Default
    private Boolean newFeatureEnabled = false;

    @Column(name = "v_2_enabled", nullable = false)
    @Builder.Default
    @Getter(value = AccessLevel.PRIVATE)
    private Boolean v2Enabled = false;

    public static String getApiUrl(String domain, String apiEndPoint) {
        if (domain.endsWith("/")) domain = domain.substring(0, domain.length() - 1);
        if (apiEndPoint.endsWith("/")) apiEndPoint = apiEndPoint.substring(0, apiEndPoint.length() - 1);
        if (apiEndPoint.startsWith("/")) apiEndPoint = apiEndPoint.substring(1);

        String apiUrl = domain + "/" + apiEndPoint;
        return apiUrl.endsWith("/") ? apiUrl : apiUrl.concat("/");
    }

    public String getApiUrl() {
        String domain = getOrganizationDomain();
        String apiEndPoint = getApiEndPoint();
        return getApiUrl(domain, apiEndPoint);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ?
                ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        OrganizationEntity that = (OrganizationEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() :
                getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
               "id = " + getId() + ", " +
               "v2Enabled = " + getV2Enabled() + ", " +
               "newFeatureEnabled = " + getNewFeatureEnabled() + ", " +
               "updatedVersion = " + getUpdatedVersion() + ", " +
               "organizationType = " + getOrganizationType() + ", " +
               "active = " + getActive() + ", " +
               "termsAndConditionsUrlBn = " + getTermsAndConditionsUrlBn() + ", " +
               "termsAndConditionsUrl = " + getTermsAndConditionsUrl() + ", " +
               "privateCertPath = " + getPrivateCertPath() + ", " +
               "privateCertAliasName = " + getPrivateCertAliasName() + ", " +
               "privateKeyStorePassword = " + getPrivateKeyStorePassword() + ", " +
               "orgDisbursementId = " + getOrgDisbursementId() + ", " +
               "apiEndPoint = " + getApiEndPoint() + ", " +
               "organizationDomain = " + getOrganizationDomain() + ", " +
               "activeInactiveDate = " + getActiveInactiveDate() + ", " +
               "cpsAPIParameters = " + getCpsAPIParameters() + ", " +
               "organizationOperator = " + getOrganizationOperator() + ", " +
               "merchantShortCode = " + getMerchantShortCode() + ", " +
               "minSubscriptionDaysToCancelSubscription = " + getMinSubscriptionDaysToCancelSubscription() + ", " +
               "merchantWalletNo = " + getMerchantWalletNo() + ", " +
               "address = " + getAddress() + ", " +
               "logo = " + getLogo() + ", " +
               "organizationCode = " + getOrganizationCode() + ")";
    }

    public boolean isV2Enabled() {
        return this.v2Enabled;
    }

    public OrganizationEntity addLocalizedName(LocalizedName ln) {
        if (Objects.isNull(localizedNames)) new ArrayList<>();
        localizedNames.add(ln);
        return this;
    }

    public String getEnLocalizeName() {
        return localizedNames.stream()
                .filter(ln -> "en".equals(ln.getLanguage()))
                .findFirst()
                .map(LocalizedName::getValue)
                .orElse(null);
    }
}