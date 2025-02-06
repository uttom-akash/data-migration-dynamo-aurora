package com.bkash.savings.models.postgres.nominee;

import com.bkash.savings.models.postgres.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;

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
public class NomineeEntity extends BaseEntity {
    @Column(name = "wallet_id", nullable = false, length = 16)
    private String walletId;

    @Column(name = "nid_number", nullable = false, length = 50)
    private String nidNumber;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    @Column(name = "relation", nullable = false, length = 8)
    private Relation relation;

    @Column(name = "last_used_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime lastUsedTime;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ?
                ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        NomineeEntity that = (NomineeEntity) o;
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
                "lastUsedTime = " + getLastUsedTime() + ", " +
                "relation = " + getRelation() + ", " +
                "dob = " + getDob() + ", " +
                "walletId = " + getWalletId() + ", " +
                "createdAt = " + getCreatedAt() + ", " +
                "lastModifiedAt = " + getLastModifiedAt() + ")";
    }
}