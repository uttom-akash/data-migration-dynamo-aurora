package com.bkash.savings.models.dto.fi;

import com.bkash.savings.models.dto.product.ProductDTO;
import com.bkash.savings.models.postgres.product.dto.ProductDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder(toBuilder = true)
public record MaturityDetailsRequestDTO(
        @NotBlank(message = "Organization code cannot be empty") String organizationCode,
        String cycleStartDate,
        /**
         * read the description {@link ProductDTOForFiMaturityDetails},
         * 
         * With existing implementation, the product contains only four fields, and all of them are string type except Term.
         */
        @Valid ProductDto product
) {
}
