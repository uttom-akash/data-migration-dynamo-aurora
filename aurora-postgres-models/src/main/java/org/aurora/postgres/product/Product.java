package org.aurora.postgres.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.aurora.postgres.base.BaseEntity;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "product", uniqueConstraints = {
        @UniqueConstraint(name = "uc_product_product_id", columnNames = {"product_id"})
})
public class Product extends BaseEntity {

    @Column(name = "product_id", nullable = false, length = 64)
    private String productId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category", length = 64)
    private String category;

    @Column(name = "price", precision = 19, scale = 2)
    private BigDecimal price;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Column(name = "listed_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private ZonedDateTime listedAt;
}
