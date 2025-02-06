package com.bkash.savings.models.postgres.product;

import com.bkash.savings.common.utils.DateTimeUtils;
import com.bkash.savings.models.postgres.account.SavingsType;
import com.bkash.savings.models.postgres.base.BaseEntity;
import com.bkash.savings.models.postgres.organization.OrganizationEntity;
import com.bkash.savings.models.postgres.product.exception.ProductInactiveException;
import com.bkash.savings.models.postgres.product.exception.ProductNotFoundException;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.bkash.savings.models.common.ApiResponseStatus.PRODUCT_NOT_ACTIVE;
import static com.bkash.savings.models.common.ApiResponseStatus.PRODUCT_NOT_FOUND;

@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product", uniqueConstraints = {
        @UniqueConstraint(name = "uc_product_product_id", columnNames = {"product_id"})
})
public class ProductEntity extends BaseEntity {
    @Column(name = "product_id", nullable = false, length = 20)
    private String productId;

    @Column(name = "product_type", nullable = false)
    private String productType;

    @Transient
    private ProductType productTypes;

    @Enumerated(EnumType.STRING)
    @Column(name = "savings_type", nullable = false, length = 6)
    private SavingsType savingsType;

    @Column(name = "interest", nullable = false)
    private Double interest;

    @Column(name = "tenure", nullable = false)
    @JdbcTypeCode(SqlTypes.TINYINT)
    private Integer tenure;

    @Enumerated(EnumType.STRING)
    @Column(name = "term", nullable = false, length = 10)
    private Term term;

    @Column(name = "auto_renewal")
    private String autoRenewal;

    @Column(name = "auto_renewal_mode")
    private String autoRenewalMode;

    @Column(name = "active_from", nullable = false)
    private LocalDate activeFrom;

    @Column(name = "de_active_from")
    private LocalDate deActiveFrom;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "total_payout", nullable = false)
    private BigDecimal totalPayout;

    @Column(name = "total_interest_earned", nullable = false)
    private BigDecimal totalInterestEarned;

    @Column(name = "organization_code", nullable = false)
    private String organizationCode;

    @Transient
    private OrganizationEntity organization;

    @Builder.Default
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "product_availability",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "availability_id", referencedColumnName = "id"))
    private List<Availability> availabilityOptions = new ArrayList<>();

    //TODO: add createdBy & modifiedBy columns


    /**
     * Validates the product entity based on different criteria.
     * <p>
     * The method performs the following validations:
     * 1. Checks if the product is within its active date range
     * 2. For DPS (Deposit Pension Scheme) products:
     * - Validates amount, tenure, and term match
     * 3. For FDR (Fixed Deposit Receipt) products:
     * - Validates tenure matches
     *
     * @throws ProductInactiveException if the product is not within its active date range
     * @throws ProductNotFoundException if the product specifications (amount, tenure, term) don't match
     *                                  for DPS or if tenure doesn't match for FDR
     */
    public void validate() {
        if (!DateTimeUtils.isWithinActiveRange(getActiveFrom(), getDeActiveFrom())) {
            throw new ProductInactiveException(PRODUCT_NOT_ACTIVE.code(), PRODUCT_NOT_ACTIVE.message());
        }
        if (getSavingsType().equals(SavingsType.DPS) &&
            (!getAmount().equals(amount) || !getTenure().equals(tenure) || !getTerm().equals(term))) {
            throw new ProductNotFoundException(PRODUCT_NOT_FOUND.code(), PRODUCT_NOT_FOUND.message());
        }
        if (getSavingsType().equals(SavingsType.DPS)
            && (!getAmount().equals(amount) || !getTenure().equals(tenure))) {
            throw new ProductNotFoundException(PRODUCT_NOT_FOUND.code(), PRODUCT_NOT_FOUND.message());
        }
        if (getSavingsType().equals(SavingsType.FDR) && !getTenure().equals(tenure)) {
            throw new ProductNotFoundException(PRODUCT_NOT_FOUND.code(), PRODUCT_NOT_FOUND.message());
        }
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ?
                ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ProductEntity that = (ProductEntity) o;
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
               "organization = " + getOrganization() + ", " +
               "organizationCode = " + getOrganizationCode() + ", " +
               "totalInterestEarned = " + getTotalInterestEarned() + ", " +
               "totalPayout = " + getTotalPayout() + ", " +
               "amount = " + getAmount() + ", " +
               "deActiveFrom = " + getDeActiveFrom() + ", " +
               "activeFrom = " + getActiveFrom() + ", " +
               "autoRenewal = " + getAutoRenewal() + ", " +
               "term = " + getTerm() + ", " +
               "tenure = " + getTenure() + ", " +
               "interest = " + getInterest() + ", " +
               "savingsType = " + getSavingsType() + ", " +
               "productType = " + getProductType() + ", " +
               "productId = " + getProductId() + ")";
    }
}
