package com.bkash.savings.models.dto.product;

import java.io.Serializable;

import com.bkash.savings.models.postgres.account.SavingsType;
import com.bkash.savings.models.postgres.product.Term;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * TODO This class is completely copied from v1. Improvement scope is available.
 */

/**
 * We are using this class in {@link com.bkash.savings.core.account.SavingsController#maturitySummary} API.
 * Where we use this class to get the product details. But there are some extra fields in this class that are not required in this API.
 * Question: Is it okay?
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Builder
public class ProductDTO implements Serializable {
    private static final long serialVersionUID = -1017480038451760047L;

    @NotBlank(message = "Product ID cannot be empty")
    private String productId;

    @NotBlank(message = "Tenure cannot be empty")
    private String tenure;

    private Term term;

    @NotBlank(message = "Amount cannot be empty")
    private String amount;

    @Builder.Default
    private SavingsType savingsType = SavingsType.DPS;

    private String autoRenewal;

    private String autoRenewalMode;
}
