package com.bkash.savings.models.dto.product;

import com.bkash.savings.models.common.ResponseStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder(toBuilder = true)
public class ProductSchemesResponse {
    private List<UniqueProductAPIResponseDTO> productSchemes;
    private ResponseStatus responseStatus;
}
