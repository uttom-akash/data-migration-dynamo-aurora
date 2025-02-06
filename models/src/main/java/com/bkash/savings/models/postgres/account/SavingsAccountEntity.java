package com.bkash.savings.models.postgres.account;

import com.bkash.savings.common.utils.DateTimeUtils;
import com.bkash.savings.models.exception.rps.RppUnsubscriptionRequestException;
import com.bkash.savings.models.postgres.base.BaseEntity;
import com.bkash.savings.models.postgres.nominee.NomineeEntity;
import com.bkash.savings.models.postgres.organization.OrganizationEntity;
import com.bkash.savings.models.postgres.product.ProductEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

import static com.bkash.savings.models.common.ApiResponseStatus.RPP_SUBSCRIPTION_REQUEST_FAILED;

@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@ToString
@Entity
@Table(name = "savings_account", indexes = {
        @Index(name = "idx_savings_account_wallet_id", columnList = "wallet_id"),
        @Index(name = "idx_savings_account_fi_account_id", columnList = "fi_account_id"),
        @Index(name = "idx_savings_account_fi_account_no", columnList = "fi_account_no"),
        @Index(name = "idx_savings_account_start_short_date_current_state", columnList = "start_short_date, " +
                                                                                         "current_state")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uc_savings_account_wallet_id_savings_id", columnNames = {"wallet_id", "savings_id"}),
        @UniqueConstraint(name = "uc_savings_account_wallet_id_fi_account_id_fi_account_no", columnNames = {
                "wallet_id", "fi_account_id", "fi_account_no"})
})
public class SavingsAccountEntity extends BaseEntity {
    @Column(name = "wallet_id", nullable = false, length = 20)
    private String walletId;

    @Column(name = "savings_id", nullable = false, length = 36)
    private String savingsId;

    @Enumerated(EnumType.STRING)
    @Column(name = "savings_type", nullable = false, length = 10)
    private SavingsType savingsType;

    @Column(name = "fi_account_id", length = 50)
    private String fiAccountId;

    @Column(name = "fi_account_no")
    private String fiAccountNo;

    @Column(name = "purpose", nullable = false)
    private String purpose;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private SavingsAccountStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_state", nullable = false, length = 100)
    private SavingsAccountCurrentState currentState;

    @Column(name = "opening_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime openingDate;

    @Column(name = "start_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime startDate;

    @Column(name = "start_short_date")
    private LocalDate startShortDate;

    @Column(name = "end_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime endDate;

    @Column(name = "maturity_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime maturityDate;

    @Column(name = "maturity_short_date")
    private LocalDate maturityShortDate;

    @Column(name = "fi_start_date_str")
    private String fiStartDateStr;

    @Column(name = "fi_end_date_str")
    private String fiEndDateStr;

    @Column(name = "fi_maturity_date_str")
    private String fiMaturityDateStr;

    @Enumerated(EnumType.STRING)
    @Column(name = "cancellation_requested_by", length = 10)
    private CancelRequester cancellationRequestedBy;

    @Column(name = "cancel_request_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime cancelRequestTime;

    @Column(name = "cancel_reason", columnDefinition = "TEXT")
    private String cancelReason;

    @Column(name = "correlation_id")
    private String correlationId;

    @Column(name = "cancellation_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime cancellationDate;

    @Column(name = "cycle_start_date", nullable = false)
    private LocalDate cycleStartDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "onboarding_type", nullable = false, length = 20)
    private OnboardingType onboardingType;

    @Column(name = "receivable_amount")
    private BigDecimal receivableAmount;

    @Column(name = "maturity_amount")
    private BigDecimal maturityAmount;

    @Column(name = "instalment_percentage")
    private Double instalmentPercentage;

    @Column(name = "effective_tenure_count")
    @JdbcTypeCode(SqlTypes.TINYINT)
    private Integer effectiveTenureCount;

    @Column(name = "product_code", nullable = false)
    private String productCode;

    @Transient
    private ProductEntity product;

    @Column(name = "nominee_id", nullable = false)
    private UUID nomineeId;

    @Transient
    private NomineeEntity nominee;

    @Column(name = "organization_code", nullable = false)
    private String organizationCode;

    @Transient
    private OrganizationEntity organization;

    @Enumerated(EnumType.STRING)
    @Column(name = "fi_status", length = 30)
    private FIStatus fiStatus;

    @Column(name = "assisted_savings_id")
    private UUID assistedSavingsId;

    @Transient
    private AssistedSavingsEntity assistedSavings;

    @Column(name = "first_trx_originator_conversation_id")
    private String firstTrxOriginatorConversationId;

    @Column(name = "first_trx_id")
    private String firstTrxId;

    @Column(name = "first_trx_date_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime firstTrxDateTime;

    @Column(name = "disbursement_trx_id")
    private String disbursementTrxId;

    @Column(name = "disbursement_originator_conversation_id")
    private String disbursementOriginatorConversationId;

    @Column(name = "disbursement_date_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime disbursementDateTime;

    @Column(name = "disbursement_amount")
    private BigDecimal disbursementAmount;

    /*
     * Do we really need this field? Maybe we can generate in run time?
     */
    @Column(name = "auto_deduction_time")
    private String autoDeductionTime;

    @Column(name = "rps_id")
    private UUID rpsId;

    @Override
    public final boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy
                ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();
        if (thisEffectiveClass != oEffectiveClass)
            return false;
        SavingsAccountEntity that = (SavingsAccountEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }

    public boolean isActive() {
        return SavingsAccountStatus.ACTIVE.equals(status);
    }

    public boolean isSavingsTypeFdr() {
        return SavingsType.FDR.equals(savingsType);
    }

    public void isCancellable() {
        if (getStatus().equals(SavingsAccountStatus.CANCELLED)) {
            throw new RppUnsubscriptionRequestException(RPP_SUBSCRIPTION_REQUEST_FAILED.code(),
                    "This savings account has already been successfully canceled.");
        }
        if (getStatus().equals(SavingsAccountStatus.MATURED)) {
            throw new RppUnsubscriptionRequestException(RPP_SUBSCRIPTION_REQUEST_FAILED.code(),
                    "The maturity of this savings account has already been reached.");
        }
        if (getStatus().equals(SavingsAccountStatus.DISBURSED)) {
            throw new RppUnsubscriptionRequestException(RPP_SUBSCRIPTION_REQUEST_FAILED.code(),
                    "The funds from this savings account have already been disbursed.");
        }

        var currentDate = DateTimeUtils.now().toLocalDate();

        if (currentDate.isAfter(maturityDate.toLocalDate()) || currentDate.isEqual(maturityDate.toLocalDate())) {
            throw new RppUnsubscriptionRequestException(RPP_SUBSCRIPTION_REQUEST_FAILED.code(),
                    "The maturity of this savings account has already been reached.");
        }
    }

    public Boolean isValidForRppUnsubscriptionResume() {
        return getCurrentState() == SavingsAccountCurrentState.RPP_UNSUBSCRIBE_FAILED
               || getCurrentState() == SavingsAccountCurrentState.RPP_UNSUBSCRIBE_PENDING;
    }

    public Boolean isValidForFiClosureResume() {
        return getCurrentState() == SavingsAccountCurrentState.FI_ACCOUNT_DELETION_FAILED
               || getCurrentState() == SavingsAccountCurrentState.FI_ACCOUNT_DELETION_PENDING;
    }

    public boolean isActiveOrInProgress() {
        return isActive() || SavingsAccountStatus.IN_PROGRESS.equals(status);
    }
}
