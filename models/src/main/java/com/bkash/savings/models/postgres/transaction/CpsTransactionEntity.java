package com.bkash.savings.models.postgres.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;

import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import org.hibernate.proxy.HibernateProxy;

import com.bkash.savings.models.postgres.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cps_transaction", indexes = {
        @Index(name = "idx_cps_transaction_trx_date", columnList = "trx_date")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uc_cps_transaction_trx_id", columnNames = {"trx_id"})
})
public class CpsTransactionEntity extends BaseEntity {

    @Column(name = "savings_id", nullable = false)
    private String savingsId;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "cps_trx_status", nullable = false)
    private CpsTransactionStatus status;

    @Column(name = "originator_conversation_id", nullable = false)
    private String originatorConversationId;

    @Column(name = "correlation_id", nullable = false)
    private String correlationId;

    @Enumerated(EnumType.STRING)
    @Column(name = "trx_channel", nullable = false, length = 5)
    private CpsTransactionChannel trxChannel;

    @Enumerated(EnumType.STRING)
    @Column(name = "trx_type", nullable = false, length = 100)
    private TransactionType trxType;

    @Column(name = "trx_id")
    private String trxId;
    
    @Column(name = "fi_account_id", nullable = false)
    private String fiAccountId;

    
    @Column(name = "fi_account_number")
    private String fiAccountNumber;

    @Column(name = "wallet_number", length = 20)
    private String walletNumber;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "trx_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime trxDate;
    
    /** This date represents the due date of a missed deposit. */
    @Column(name = "trx_due_date", nullable = true)
    private LocalDate trxDueDate;

    @Column(name = "request_initiated_time", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime request_initiated_time;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_source", length = 10, nullable = false)
    private TransactionSource trxSource;
    
    @Column(name = "org_short_code", nullable = false)
    private String orgCode;

    @Column(name = "reference_cps_trx_id")
    private String referenceCpsTrxId;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ?
                ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        CpsTransactionEntity that = (CpsTransactionEntity) o;
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
                "correlationId = " + getCorrelationId() + ", " +
                "savingsId = " + getSavingsId() + ", " +
                "amount = " + getAmount() + ", " +
                "status = " + getStatus() + ", " +
                "originatorConversationId = " + getOriginatorConversationId() + ", " +
                "trxChannel = " + getTrxChannel() + ", " +
                "trxType = " + getTrxType() + ", " +
                "trxId = " + getTrxId() + ", " +
                "walletNumber = " + getWalletNumber() + ", " +
                "receiver = " + getReceiver() + ", " +
                "trxDate = " + getTrxDate() + ", " +
                "trxDueDate = " + getTrxDueDate() + ", " +
                "trxSource = " + getTrxSource() + ", " +
                "request_initiated_time = " + getRequest_initiated_time() + ", " +
                "createdAt = " + getCreatedAt() + ", " +
                "lastModifiedAt = " + getLastModifiedAt() + ")";
    }
}