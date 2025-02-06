package com.bkash.savings.models.postgres.product;

import com.bkash.savings.models.dto.product.ServiceRequesterType;
import com.bkash.savings.models.postgres.account.SavingsType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

/**
 * Possible value of productType:
 * <ul>
 *     <li><code>Short Term Product</code></li>
 * </ul>
 *
 * @param organizationCode
 * @param activeScheme
 * @param productType
 * @param savingsType
 * @param availabilityOption
 */
@Builder
public record ProductFilter(
        @NotNull String organizationCode,
        Boolean activeScheme,
        String productType, // Short Term Product, Long Term Product
        String savingsType,
        @NotNull ServiceRequesterType availabilityOption
) {
    public ProductFilter {
        if (savingsType == null) {
            savingsType = SavingsType.DPS.name();
        }
    }

    public static ProductFilter getDefault(String organizationCode) {
        return new ProductFilter(organizationCode, true, null,
                SavingsType.DPS.name(), null);
    }
}
