package com.bkash.savings.models.dto.queue;

import jakarta.validation.constraints.NotBlank;

public record AssistedCommissionQueueMessage(
        @NotBlank String customerWalletNumber,
        @NotBlank String assistedRequestIdRef,
        @NotBlank String traceId
) {
}