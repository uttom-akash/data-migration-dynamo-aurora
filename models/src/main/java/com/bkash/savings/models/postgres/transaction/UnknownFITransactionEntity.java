package com.bkash.savings.models.postgres.transaction;

import com.bkash.savings.models.dto.fi.FiTransactionstatus;
import com.bkash.savings.models.postgres.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import org.hibernate.proxy.HibernateProxy;

import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "unknown_transaction_entity")
public class UnknownFITransactionEntity extends BaseEntity {

    @Column(name = "fi_account_id", nullable = false)
    private String fiAccountId;

    @Column(name = "org_short_code", nullable = false)
    private String orgCode;

    @Column(name = "cps_trx_id", nullable = false, unique = true)
    private String cpsTrxId;

    @Column(name = "cps_trx_date", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime cpsTrxDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "fi_transaction_type", length = 20)
    private TransactionType fiTransactionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "fi_transaction_status", length = 20)
    private FiTransactionstatus fiTransactionStatus;

    @Column(name = "fi_trx_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime fiTrxDate; // for DISBURSEMENT type transaction, this will represent the closing trx date

    @Column(name = "fi_trx_id")
    private String fiTrxId; // for DISBURSEMENT type transaction, this will represent the closing trx id

    @Column(name = "reference_cps_trx_id")
    private String referenceCpsTrxId; // for REFUND type transaction, this will represent the original transaction id

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        UnknownFITransactionEntity that = (UnknownFITransactionEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
               "id = " + getId() + ", " +
               "fiAccountId = " + getFiAccountId() + ", " +
               "orgCode = " + getOrgCode() + ", " +
               "cpsTrxId = " + getCpsTrxId() + ", " +
               "cpsTrxDate = " + getCpsTrxDate() + ", " +
               "fiTransactionType = " + getFiTransactionType() + ", " +
               "fiTransactionStatus = " + getFiTransactionStatus() + ", " +
               "fiTrxDate = " + getFiTrxDate() + ", " +
               "fiTrxId = " + getFiTrxId() + ", " +
               "referenceCpsTrxId = " + getReferenceCpsTrxId() + ")";
    }
}
