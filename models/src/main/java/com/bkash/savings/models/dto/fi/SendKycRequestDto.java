package com.bkash.savings.models.dto.fi;

import com.bkash.savings.models.dto.gql.Customer;
import com.bkash.savings.models.postgres.nominee.dto.NomineeInfoForFI;
import com.bkash.savings.models.postgres.product.dto.ProductInfoForFI;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.io.Serializable;

@Builder(toBuilder = true)
public record SendKycRequestDto(@NotBlank String requestId, @NotBlank String organizationCode,
                                @NotBlank String walletNo,
                                Customer customer,
                                NomineeInfoForFI nominee,
                                ProductInfoForFI product,
                                // need clarification on e-tin
                                String eTin,
                                @NotBlank String cycleStartDate) implements Serializable {
}
