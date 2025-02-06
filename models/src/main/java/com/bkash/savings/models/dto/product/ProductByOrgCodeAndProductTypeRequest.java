package com.bkash.savings.models.dto.product;

import com.bkash.savings.models.postgres.account.SavingsType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductByOrgCodeAndProductTypeRequest {
    @NotBlank
    private String orgCode;

    @NotBlank
    private String productType;

    @NotBlank
    private String savingsType = SavingsType.DPS.name();
}
