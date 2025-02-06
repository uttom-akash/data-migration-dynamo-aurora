package com.bkash.savings.models.postgres.transaction;

import com.bkash.savings.models.dto.fi.FiTransactionstatus;
import com.bkash.savings.models.postgres.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
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

@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dps_transaction", uniqueConstraints = {
        @UniqueConstraint(name = "uc_dps_transaction_orgCode_fiTrxId", columnNames = {"org_short_code", "fi_trx_id"}),
})
public class DpsTransactionEntity extends BaseEntity {

    @Column(name = "savings_id", nullable = false)
    private String savingsId;

    @Column(name = "cps_trx_id", nullable = false, unique = true)
    private String cpsTrxId;

    @Column(name = "cps_trx_date", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime cpsTrxDate;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "fi_trx_status", nullable = false, length = 20)
    private FiTransactionstatus fiStatus = FiTransactionstatus.UNKNOWN;

    @Enumerated(EnumType.STRING)
    @Column(name = "trx_status", nullable = false, length = 20)
    private DpsTransactionStatus status;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "bk_plan_no", nullable = false)
    @JdbcTypeCode(SqlTypes.SMALLINT)
    private Integer bkPlanNo;

    @Column(name = "fi_plan_no")
    @JdbcTypeCode(SqlTypes.SMALLINT)
    private Integer fiPlanNo;

    @Column(name = "fi_trx_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime fiTrxDate;

    @Column(name = "fi_trx_id")
    private String fiTrxId;

    @Column(name = "fi_account_id", nullable = false)
    private String fiAccountId;

    @Column(name = "fi_account_number")
    private String fiAccountNumber;

    @Column(name = "reference_cps_conversation_id")
    private String referenceCpsConversationId;

    /**
     * If this fields is not null, then this transaction is refunded or reversed.
     */
    @Column(name = "reference_cps_trx_id")
    private String referenceCpsTrxId;

    /**
     * If this fields is not null, then this transaction is refunded or reversed.
     */
    @Column(name = "reference_cps_trx_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime referenceCpsTrxDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "trx_type", nullable = false, length = 20)
    private TransactionType type;

    @Column(name = "org_short_code", nullable = false)
    private String orgCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_source", length = 10, nullable = false)
    private TransactionSource trxSource;

    @Column(name = "rpp_payment_id")
    private Long rppPaymentId;

    @Column(name = "remarks")
    private String remarks;


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ?
                ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DpsTransactionEntity that = (DpsTransactionEntity) o;
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
               "type = " + getType() + ", " +
               "referenceCpsTrxId = " + getReferenceCpsTrxId() + ", " +
               "fiTrxId = " + getFiTrxId() + ", " +
               "fiTrxDate = " + getFiTrxDate() + ", " +
               "fiPlanNo = " + getFiPlanNo() + ", " +
               "bkPlanNo = " + getBkPlanNo() + ", " +
               "dueDate = " + getDueDate() + ", " +
               "status = " + getStatus() + ", " +
               "amount = " + getAmount() + ", " +
               "cpsTrxDate = " + getCpsTrxDate() + ", " +
               "cpsTrxId = " + getCpsTrxId() + ", " +
               "savingsId = " + getSavingsId() + ", " +
               "createdAt = " + getCreatedAt() + ", " +
               "lastModifiedAt = " + getLastModifiedAt() + ")";
    }
}