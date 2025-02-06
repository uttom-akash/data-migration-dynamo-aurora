package com.bkash.savings.models.postgres.product.dto;

import com.bkash.savings.models.postgres.account.SavingsType;
import com.bkash.savings.models.postgres.product.Term;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link com.bkash.savings.models.postgres.product.ProductEntity}
 */
@Builder
public record ProductInfoForFI(@NotBlank(message = "Product ID can not be empty") String productId,
                               @NotNull(message = "Savings type can not be empty") SavingsType savingsType,
                               @Positive Integer tenure, @NotNull(message = "Term can not be empty") Term term,
                               String amount, Boolean autoRenewal) implements Serializable {
}