package org.aurora.postgres.deposit_nominee;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.aurora.postgres.base.BaseEntity;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "nominee", indexes = {
        @Index(name = "idx_nominee_wallet_id", columnList = "wallet_id")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uc_nominee_id_wallet_id", columnNames = {"id", "wallet_id"}),
        @UniqueConstraint(name = "uc_nominee_wallet_id_nid_number", columnNames = {"wallet_id", "nid_number"})
})
public class DepositNominee extends BaseEntity {
    @Column(name = "wallet_id", nullable = false, length = 16)
    private String walletId;

    @Column(name = "nid_number", nullable = false, length = 50)
    private String nidNumber;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    @Column(name = "relation", nullable = false, length = 8)
    private NomineeRelation relation;

    @Column(name = "last_used_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime lastUsedTime;
}