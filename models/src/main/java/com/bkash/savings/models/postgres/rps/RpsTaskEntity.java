package com.bkash.savings.models.postgres.rps;

import com.bkash.savings.models.postgres.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rps_task", uniqueConstraints = {
        @UniqueConstraint(name = "uc_rps_task_id", columnNames = {"task_id"})
})
public class RpsTaskEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "rps_id", referencedColumnName = "rps_id", nullable = false)
//    @NotFound(action = org.hibernate.annotations.NotFoundAction.IGNORE)
    private RpsEntity rpsEntity;

    @Column(name = "rpp_sub_req_id", nullable = false, length = 40)
    private String rppSubReqId;

    @Column(name = "task_id", nullable = false, length = 40)
    private String taskId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 40)
    private RpsTaskStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "q_status", nullable = false, length = 40)
    private RpsTaskQStatus queueStatus;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "termination_date")
    private LocalDate terminationDate;

    @Column(name = "rpp_sub_id")
    private long rppSubId;

    @Column(name = "last_req_time")
    private LocalDate lastReqTime;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        RpsTaskEntity that = (RpsTaskEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    public RpsTaskEntity getRpsEntityFromEntity( RpsTaskEntity rpsTaskEntity, String subReqId) {
        return new RpsTaskEntity().toBuilder()
                .id(rpsTaskEntity.getId())
                .rpsEntity(rpsTaskEntity.getRpsEntity())
                .rppSubReqId(subReqId)
                .taskId(subReqId)
                .status(rpsTaskEntity.getStatus())
                .queueStatus(RpsTaskQStatus.NotInQueue)
                .startDate(rpsTaskEntity.getStartDate())
                .endDate(rpsTaskEntity.getEndDate())
                .terminationDate(rpsTaskEntity.getTerminationDate())
                .rppSubId(rpsTaskEntity.getRppSubId())
                .lastReqTime(rpsTaskEntity.getLastReqTime())
                .createdAt(rpsTaskEntity.getCreatedAt())
                .lastModifiedAt(rpsTaskEntity.getLastModifiedAt())
                .build();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + getId() + ", " +
                "rpsEntity = " + getRpsEntity() + ", " +
                "rppSubReqId = " + getRppSubReqId() + ", " +
                "taskId = " + getTaskId() + ", " +
                "status = " + getStatus() + ", " +
                "qStatus = " + queueStatus + ", " +
                "startDate = " + getStartDate() + ", " +
                "endDate = " + getEndDate() + ", " +
                "terminationDate = " + getTerminationDate() + ", " +
                "rppSubId = " + getRppSubId() + ", " +
                "lastReqTime = " + getLastReqTime() + ", " +
                "createdAt = " + getCreatedAt() + ", " +
                "lastModifiedAt = " + getLastModifiedAt() + ")";
    }
}
