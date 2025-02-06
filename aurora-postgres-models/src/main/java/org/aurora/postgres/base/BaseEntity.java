package org.aurora.postgres.base;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
//@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class BaseEntity implements Serializable {
    @Id
    @Column(nullable = false)
    @IDGenerator
    private UUID id;

    @Version
    @Column(name = "version")
    private Integer version;

    @CreatedDate
    @Column(name = "created_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime createdAt;

    @LastModifiedDate
    @Column(name = "last_modified_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime lastModifiedAt;

}