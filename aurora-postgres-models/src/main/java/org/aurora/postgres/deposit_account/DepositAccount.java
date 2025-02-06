package org.aurora.postgres.deposit_account;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.aurora.postgres.base.BaseEntity;
import org.aurora.postgres.deposit_nominee.DepositNominee;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@ToString
@Entity
@Table(name = "savings_account", indexes = {
        @Index(name = "idx_savings_account_wallet_id", columnList = "wallet_id")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uc_savings_account_wallet_id_savings_id", columnNames = {"wallet_id", "savings_id"})
})
public class DepositAccount extends BaseEntity {
    @Column(name = "wallet_id", nullable = false, length = 20)
    private String walletId;

    @Column(name = "savings_id", nullable = false, length = 36)
    private String savingsId;

    @Enumerated(EnumType.STRING)
    @Column(name = "savings_type", nullable = false, length = 10)
    private DepositType savingsType;


    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private DepositAccountStatus status;

    @Column(name = "opening_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime openingDate;

    @Column(name = "end_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime endDate;

    @Column(name = "maturity_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime maturityDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "cancellation_requested_by", length = 10)
    private CancelRequester cancellationRequestedBy;

    @Column(name = "cancel_request_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime cancelRequestTime;

    @Column(name = "cancel_reason", columnDefinition = "TEXT")
    private String cancelReason;

    @Column(name = "cancellation_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime cancellationDate;

    @Column(name = "cycle_start_date", nullable = false)
    private LocalDate cycleStartDate;


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

    @Column(name = "nominee_id", nullable = false)
    private UUID nomineeId;

    @Transient
    private DepositNominee nominee;

    @Column(name = "organization_code", nullable = false)
    private String organizationCode;

    @Column(name = "first_trx_originator_conversation_id")
    private String firstTrxOriginatorConversationId;

    @Column(name = "first_trx_id")
    private String firstTrxId;

    @Column(name = "first_trx_date_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime firstTrxDateTime;
}
