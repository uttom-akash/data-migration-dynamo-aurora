package com.bkash.savings.models.dto.product;

import com.bkash.savings.models.common.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class ProductByOrgCodeAndProductTypeResponse {
    private List<SchemeResponseDTO> productSchemes;
    private ResponseStatus responseStatus;
}
