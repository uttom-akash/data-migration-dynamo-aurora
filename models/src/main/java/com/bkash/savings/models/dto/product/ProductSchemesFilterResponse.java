package com.bkash.savings.models.dto.product;

import java.util.List;

import com.bkash.savings.models.common.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSchemesFilterResponse {

    // List of filtered product schemes.
    private List<ProductSchemeDTO> productSchemes;

    // Response status.
    private ResponseStatus responseStatus;
}
