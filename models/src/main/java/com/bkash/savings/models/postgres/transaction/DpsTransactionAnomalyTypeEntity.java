package com.bkash.savings.models.postgres.transaction;

import com.bkash.savings.models.postgres.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dps_transaction_anomaly_type")
public class DpsTransactionAnomalyTypeEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "dps_transaction_anomaly_type", nullable = false, unique = true)
    private DpsTransactionAnomalyType dpsTransactionAnomalyType;

    @Column(name = "description", nullable = false)
    private String description;


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DpsTransactionAnomalyTypeEntity that = (DpsTransactionAnomalyTypeEntity) o;
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
               "dpsTransactionAnomalyType = " + getDpsTransactionAnomalyType() + ", " +
               "description = " + getDescription() + ")";
    }
}