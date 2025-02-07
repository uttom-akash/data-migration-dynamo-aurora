package org.aurora.postgres.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import org.aurora.postgres.base.BaseEntity;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;


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
public class TransactionLog extends BaseEntity {

    @Column(name = "savings_id", nullable = false)
    private String savingsId;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "cps_trx_status", nullable = false)
    private TransactionLogStatus status;

    @Column(name = "correlation_id", nullable = false)
    private String correlationId;

    @Enumerated(EnumType.STRING)
    @Column(name = "trx_channel", nullable = false, length = 5)
    private TransactionLogChannel trxChannel;

    @Enumerated(EnumType.STRING)
    @Column(name = "trx_type", nullable = false, length = 100)
    private TransactionType trxType;

    @Column(name = "trx_id")
    private String trxId;

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
    
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_source", length = 10, nullable = false)
    private TransactionSource trxSource;
    
    @Column(name = "org_short_code", nullable = false)
    private String orgCode;

    @Column(name = "reference_cps_trx_id")
    private String referenceCpsTrxId;
}