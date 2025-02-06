package com.bkash.savings.models.postgres.account;

import com.bkash.savings.models.postgres.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@Table(name = "account_details_sync", uniqueConstraints = {
        @UniqueConstraint(name = "uc_account_details_sync_wallet_id_savings_id", columnNames = {"wallet_id", "savings_id"}),
        @UniqueConstraint(name = "uc_account_details_sync_organization_code_fi_account_id", columnNames = {"organization_code", "fi_account_id"})
})
public class AccountDetailsSyncerEntity extends BaseEntity {

    @Column(name = "wallet_id", nullable = false, length = 20)
    private String walletId;

    @Column(name = "savings_id", nullable = false, length = 36)
    private String savingsId;

    @Column(name = "organization_code", nullable = false)
    private String organizationCode;

    @Column(name = "fi_account_id", length = 50, nullable = false)
    private String fiAccountId;

    @Column(name = "status", length = 50, nullable = false)
    private SavingsAccountStatus status;

    @Column(name = "current_amount")
    private BigDecimal currentAmount;

    @Column(name = "current_deposited_amount")
    private BigDecimal currentDepositedAmount;

    @Column(name = "current_interest_amount")
    private BigDecimal currentInterestAmount;

    @Column(name = "last_sync_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime lastSyncTime;

    @Column(name = "maturity_amount")
    private Double maturityAmount;

    @Column(name = "maturity_date")
    private ZonedDateTime maturityDate;

    @Column(name = "tax_amount")
    private Double taxAmount;

    @Column(name = "excise_duty")
    private Double exciseDuty;

    @Column(name = "total_instalment_no")
    private Integer totalInstalmentNo;

    @Column(name = "interest_rate")
    private Double interestRate;

    @Column(name = "instalment_percentage")
    private Double instalmentPercentage;

    @Column(name = "miss_payment")
    private Boolean missPayment;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "totalDepositAmount")
    private Double totalDepositAmount;

    @Column(name = "total_interest_earning")
    private Double totalInterestEarning;
}
