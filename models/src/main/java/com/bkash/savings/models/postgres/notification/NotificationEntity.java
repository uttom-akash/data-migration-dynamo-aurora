package com.bkash.savings.models.postgres.notification;

import com.bkash.savings.models.postgres.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "notification", uniqueConstraints = {
        @UniqueConstraint(name = "uc_notification_event", columnNames = {"event"})
})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE,
        region = "entity.notification")
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEntity extends BaseEntity {
    @Column(name = "event", nullable = false, length = 30)
    private String event;

    @Column(name = "expiry_time", nullable = false)
    private Integer expiryTime;

    @Column(name = "title", nullable = false, length = 40)
    private String title;

    @Column(name = "logo_url", nullable = false, columnDefinition = "TEXT")
    private String logoUrl;

    @Column(name = "image_url", nullable = false, columnDefinition = "TEXT")
    private String imageUrl;

    @Column(name = "header_text", nullable = false, length = 100)
    private String headerText;

    @Column(name = "header_details", nullable = false, length = 150)
    private String headerDetails;

    @Column(name = "details_text", columnDefinition = "TEXT", nullable = false)
    private String detailsText;

    @Column(name = "action_text", nullable = false, length = 30)
    private String actionText;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ?
                ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        NotificationEntity that = (NotificationEntity) o;
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
                "event = " + getEvent() + ", " +
                "expiryTime = " + getExpiryTime() + ", " +
                "title = " + getTitle() + ", " +
                "logoUrl = " + getLogoUrl() + ", " +
                "imageUrl = " + getImageUrl() + ", " +
                "headerText = " + getHeaderText() + ", " +
                "headerDetails = " + getHeaderDetails() + ", " +
                "detailsText = " + getDetailsText() + ", " +
                "actionText = " + getActionText() + ", " +
                "createdDate = " + getCreatedAt() + ", " +
                "lastModifiedDate = " + getLastModifiedAt() + ")";
    }
}
