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

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dps_anomaly_transaction")
public class DpsAnomalyTransactionEntity extends BaseEntity {

    @Column(nullable = false)
    private String fiAccountId;

    @Column(nullable = false)
    private String orgCode;

    @Column(nullable = false, unique = true)
    private String cpsTrxId;

    @Column(nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime cpsTrxDate;

    private String fiAccountNo;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TransactionType fiTransactionType;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private FiTransactionstatus fiTransactionStatus;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime fiTrxDate; // for DISBURSEMENT type transaction, this will represent the closing trx date

    private String fiTrxId; // for DISBURSEMENT type transaction, this will represent the closing trx id

    private String referenceCpsTrxId; // for REFUND type transaction, this will represent the original transaction id

    private String remarks; // for REFUND type transaction, this will represent the original transaction id


    @ManyToMany
    @JoinTable(name = "dps_anomaly_transaction_anomaly_type_association",
            joinColumns = @JoinColumn(name = "anomaly_transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "anomaly_type_id"))
    private List<DpsTransactionAnomalyTypeEntity> anomalyTypes;

    public DpsAnomalyTransactionEntity addAnomaly(DpsTransactionAnomalyTypeEntity anomalyType) {
        if (Objects.isNull(anomalyTypes)) anomalyTypes = new ArrayList<>();
        anomalyTypes.add(anomalyType);
        return this;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
               "id = " + getId() + ", " +
               "fiAccountId = " + getFiAccountId() + ", " +
               "orgCode = " + getOrgCode() + ", " +
               "cpsTrxId = " + getCpsTrxId() + ", " +
               "cpsTrxDate = " + getCpsTrxDate() + ", " +
               "fiAccountNo = " + getFiAccountNo() + ", " +
               "fiTransactionType = " + getFiTransactionType() + ", " +
               "fiTransactionStatus = " + getFiTransactionStatus() + ", " +
               "fiTrxDate = " + getFiTrxDate() + ", " +
               "fiTrxId = " + getFiTrxId() + ", " +
               "referenceCpsTrxId = " + getReferenceCpsTrxId() + ", " +
               "remarks = " + getRemarks() + ")";
    }

    public boolean hasAnomalies() {
        return Objects.nonNull(anomalyTypes) && !anomalyTypes.isEmpty();
    }
}
