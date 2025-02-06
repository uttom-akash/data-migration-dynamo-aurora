package com.bkash.savings.models.dto.fi;

import lombok.Builder;

@Builder(toBuilder = true)
public record CreateFiAccountRequestDto(
        String organizationCode,
        String trxId,
        String amount,
        String accountId,
        String correlationId,
        String trxDate,
        String subscriptionRequestId,
        String walletNo,
        String savingsType,
        String savingsId,
        String cycleStartDate
) {
}
