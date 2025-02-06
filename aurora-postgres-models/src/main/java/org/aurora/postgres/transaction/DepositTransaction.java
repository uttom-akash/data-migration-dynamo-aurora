package org.aurora.postgres.transaction;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.aurora.postgres.base.BaseEntity;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dps_transaction", uniqueConstraints = {
        @UniqueConstraint(name = "uc_dps_transaction_orgCode_fiTrxId", columnNames = {"org_short_code", "fi_trx_id"}),
})
public class DepositTransaction extends BaseEntity {

    @Column(name = "savings_id", nullable = false)
    private String savingsId;

    @Column(name = "cps_trx_id", nullable = false, unique = true)
    private String cpsTrxId;

    @Column(name = "cps_trx_date", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime cpsTrxDate;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "trx_status", nullable = false, length = 20)
    private DepositTransactionStatus status;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "bk_plan_no", nullable = false)
    @JdbcTypeCode(SqlTypes.SMALLINT)
    private Integer bkPlanNo;

    @Column(name = "reference_cps_conversation_id")
    private String referenceCpsConversationId;

    @Column(name = "reference_cps_trx_id")
    private String referenceCpsTrxId;

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
}