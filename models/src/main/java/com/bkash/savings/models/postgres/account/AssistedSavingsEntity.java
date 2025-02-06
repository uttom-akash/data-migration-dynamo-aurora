package com.bkash.savings.models.postgres.account;

import com.bkash.savings.models.postgres.base.BaseEntity;
import com.bkash.savings.models.postgres.nominee.NomineeEntity;
import com.bkash.savings.models.postgres.organization.OrganizationEntity;
import com.bkash.savings.models.postgres.product.ProductEntity;
import com.bkash.savings.models.postgres.transaction.CpsTransactionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "assisted_savings", indexes = {
        @Index(name = "idx_assisted_savings_requester_wallet_id", columnList = "requester_wallet_id"),
        // why?
        @Index(name = "idx_assisted_savings_requester_wallet_id_savings_type", columnList = "requester_wallet_id, savings_type"),
        @Index(name = "idx_assisted_savings_requester_wallet_id_status", columnList = "requester_wallet_id, status"),
        @Index(name = "idx_assisted_savings_requester_wallet_id_organization_code", columnList = "requester_wallet_id, organization_code"),
        @Index(name = "idx_assisted_savings_requester_wallet_id_created_date", columnList = "requester_wallet_id, created_date"),
        @Index(name = "idx_assisted_savings_request_id_requester_wallet_id", columnList = "request_id, requester_wallet_id")
})
public class AssistedSavingsEntity extends BaseEntity {
    @Column(name = "request_id", nullable = false)
    private String requestId;

    @Column(name = "requester_wallet_id", nullable = false, length = 20)
    private String requesterWalletId;

    @Column(name = "customer_wallet_id", nullable = false, length = 20)
    private String customerWalletId;

    @Enumerated(EnumType.STRING)
    @Column(name = "savings_type", nullable = false, length = 5)
    private SavingsType savingsType;

    @Column(name = "product_code", nullable = false)
    private String productCode;

    @Transient
    private ProductEntity product;

    @Column(name = "nominee_id", nullable = false)
    private UUID nomineeId;

    @Transient
    private NomineeEntity nominee;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private AssistedSavingsStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "assisted_requester_type", nullable = false, length = 20)
    private AssistedRequesterType assistedRequesterType;

    @Column(name = "requester_name", nullable = false)
    private String requesterName;

    @Column(name = "organization_code", nullable = false)
    private String organizationCode;

    @Transient
    private OrganizationEntity organization;

    @Column(name = "cycle_start_date", nullable = false)
    private LocalDate cycleStartDate;

    @Column(name = "reported_by_customer")
    private Boolean reportedByCustomer;

    @Column(name = "first_deposit_id")
    private String firstTransactionId;

    @Transient
    private CpsTransactionEntity firstDepositInfo;

    @Column(name = "earning_received_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime earningReceivedDate;

    @Column(name = "commission_amount")
    private BigDecimal commissionAmount;

    @Column(name = "maturity_date", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime maturityDate;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "effective_tenure_count")
    @JdbcTypeCode(SqlTypes.TINYINT)
    private Integer effectiveTenureCount;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ?
                ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        AssistedSavingsEntity that = (AssistedSavingsEntity) o;
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
                "requesterWalletId = " + getRequesterWalletId() + ", " +
                "customerWalletId = " + getCustomerWalletId() + ", " +
                "savingsType = " + getSavingsType() + ", " +
                "status = " + getStatus() + ", " +
                "assistedRequesterType = " + getAssistedRequesterType() + ", " +
                "requesterName = " + getRequesterName() + ", " +
                "cycleStartDate = " + getCycleStartDate() + ", " +
                "reportedByCustomer = " + getReportedByCustomer() + ", " +
                "earningReceivedDate = " + getEarningReceivedDate() + ", " +
                "commissionAmount = " + getCommissionAmount() + ", " +
                "maturityDate = " + getMaturityDate() + ", " +
                "message = " + getMessage() + ", " +
                "purpose = " + getPurpose() + ", " +
                "effectiveTenureCount = " + getEffectiveTenureCount() + ", " +
                "createdAt = " + getCreatedAt() + ", " +
                "lastModifiedAt = " + getLastModifiedAt() + ")";
    }
}