package com.bkash.savings.models.dto.fi;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AccountDeletionRequestDto(
        @NotBlank String reason,
        @NotBlank String accountNo,
        @NotBlank String walletNo,
        @NotBlank String organizationCode,
        String cancelRequestedBy
) {
}
