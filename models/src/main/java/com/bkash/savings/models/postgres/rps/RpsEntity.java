package com.bkash.savings.models.postgres.rps;

import com.bkash.savings.models.postgres.base.BaseEntity;
import com.bkash.savings.models.postgres.organization.OrganizationType;
import com.bkash.savings.models.postgres.product.Term;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rps", uniqueConstraints = {
        @UniqueConstraint(name = "uc_rps_id", columnNames = {"rps_id"})
})
public class RpsEntity extends BaseEntity {

    @Column(name = "savings_id", nullable = false, length = 36)
    private String savingsId;

    @Column(name = "rps_id", nullable = false, length = 36)
    private String rpsId;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "cycle_start_date", nullable = false)
    private LocalDate cycleStartDate;

    @Column(name = "cycle_end_date", nullable = false)
    private LocalDate cycleEndDate;

    @Column(name = "activation_date", nullable = false)
    private LocalDate activationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "organization_type", nullable = false, length = 20)
    private OrganizationType organizationType;

    @Column(name = "wallet_id", nullable = false, length = 20)
    private String walletId;

    @Column(name = "fi_account_id", nullable = false, length = 50)
    private String fiAccountId;

    @Enumerated(EnumType.STRING)
    @Column(name = "rps_status", nullable = false, length = 40)
    private RpsStatus rpsStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "term", nullable = false, length = 10)
    private Term term;

    @Column(name = "organization_code", nullable = false, length = 20)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String organizationCode;

    @Column(name = "merchant_short_code", nullable = false, length = 20)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String merchantShortCode;

    @Column(name = "cancel_attempted", nullable = false)
    @Builder.Default
    private Boolean cancelAttempted = false;

    @Column(name = "tasks_remaining", nullable = false)
    @Builder.Default
    private Integer tasksRemaining = 0;

    @OneToMany(mappedBy = "rpsEntity", fetch = FetchType.LAZY)
    private Set<RpsTaskEntity> rpsTasks;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        RpsEntity rpsEntity = (RpsEntity) o;
        return getId() != null && Objects.equals(getId(), rpsEntity.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + getId() + ", " +
                "savingsId = " + getSavingsId() + ", " +
                "rpsId = " + getRpsId() + ", " +
                "amount = " + getAmount() + ", " +
                "cycleStartDate = " + getCycleStartDate() + ", " +
                "cycleEndDate = " + getCycleEndDate() + ", " +
                "activationDate = " + getActivationDate() + ", " +
                "organizationType = " + getOrganizationType() + ", " +
                "walletId = " + getWalletId() + ", " +
                "fiAccountId = " + getFiAccountId() + ", " +
                "rpsStatus = " + getRpsStatus() + ", " +
                "term = " + getTerm() + ", " +
                "organizationCode = " + getOrganizationCode() + ", " +
                "merchantShortCode = " + getMerchantShortCode() + ", " +
                "cancelAttempted = " + getCancelAttempted() + ", " +
                "createdAt = " + getCreatedAt() + ", " +
                "lastModifiedAt = " + getLastModifiedAt() + ")";
    }
}
