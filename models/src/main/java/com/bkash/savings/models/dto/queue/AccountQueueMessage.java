package com.bkash.savings.models.dto.queue;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AccountQueueMessage(
        @NotBlank String walletNumber,
        @NotBlank String savingId,
        @NotBlank String traceId
) {
}