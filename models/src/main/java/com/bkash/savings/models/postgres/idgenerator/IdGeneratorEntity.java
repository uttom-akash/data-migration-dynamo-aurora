package com.bkash.savings.models.postgres.idgenerator;

import com.bkash.savings.models.postgres.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "id_generator", indexes = {
        @Index(name = "idx_id_generator_pk", columnList = "pk")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uc_id_generator_sk_pk", columnNames = {"sk", "pk"})
})
public class IdGeneratorEntity extends BaseEntity {
    @Column(name = "pk", nullable = false)
    private String pk;

    @Column(name = "sk", nullable = false)
    private String sk;

    @Column(name = "value")
    private Long value;

    public long incrementValue() {
        if (value == null) {
            value = 1L;
        } else {
            value++;
        }
        return value;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        IdGeneratorEntity that = (IdGeneratorEntity) o;
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
                "pk = " + getPk() + ", " +
                "sk = " + getSk() + ", " +
                "value = " + getValue() + ")";
    }
}