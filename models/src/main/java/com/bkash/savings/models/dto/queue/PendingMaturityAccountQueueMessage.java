package com.bkash.savings.models.dto.queue;

import lombok.Builder;

@Builder
public record PendingMaturityAccountQueueMessage(String walletNumber, String savingsId, String traceId) {
}