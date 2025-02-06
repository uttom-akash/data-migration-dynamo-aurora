package com.bkash.savings.models.postgres.product.dto;

import com.bkash.savings.models.postgres.account.SavingsType;
import com.bkash.savings.models.postgres.product.Term;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.bkash.savings.models.postgres.product.ProductEntity}
 * // TODO: Question
 * <ul>
 *     <li>Will we use this everywhere?
 *     For example, when we project dto from db, send request to bank, received request from app, etc.</li>
 *     <li>{@link com.bkash.savings.core.account.SavingsController#maturitySummary}</li>
 *     <li>{@link com.bkash.savings.models.dto.fi.MaturityDetailsRequestDTO}</li>
 * </ul>
 */
@Builder(toBuilder = true)
public record ProductDto(
        @NotBlank(message = "Product ID cannot be empty") String productId,
        @NotBlank(message = "Tenure cannot be empty") String tenure,
        Term term,
        BigDecimal amount,
        SavingsType savingsType,
        String autoRenewal,
        String autoRenewalMode,
        @JsonIgnore String productType, // This field is only use in Details API. SavingsDetailsDtoProjection use it.
        @JsonIgnore BigDecimal totalPayout, // This field is only used in the Details API. When we can't get data from the bank, for v1 type org
        @JsonIgnore BigDecimal totalInterest, // This field is only used in the Details API. When we can't get data from the bank, for v1 type org
        @JsonIgnore Double integerRate
) implements Serializable {
    @Serial
    private static final long serialVersionUID = -1017480038451760047L;

    /**
     * This is used by <code>Details</code> API.
     */
    public ProductDto(String productId, String tenure, Term term, BigDecimal amount,
                      SavingsType savingsType, String autoRenewal, String autoRenewalMode, String productType,
                      BigDecimal totalPayout, BigDecimal totalInterest, Double integerRate) {
        this.productId = productId;
        this.tenure = tenure;
        this.term = term;
        this.amount = amount;
        this.savingsType = savingsType != null ? savingsType : SavingsType.DPS; // Default value for null
        this.autoRenewal = autoRenewal;
        this.autoRenewalMode = autoRenewalMode;
        this.productType = productType;
        this.totalPayout = totalPayout;
        this.totalInterest = totalInterest;
        this.integerRate = integerRate;
    }

    /**
     * This is used by <code>List</code> and <code>Dashboard</code> APIs.
     */
    public static ProductDto create(String productId, String tenure, Term term, BigDecimal amount,
                                    SavingsType savingsType, String autoRenewal, String autoRenewalMode) {
        return new ProductDto(productId, tenure, term, amount,
                savingsType != null ? savingsType : SavingsType.DPS,
                autoRenewal, autoRenewalMode, null, null, null, null);
    }
}
