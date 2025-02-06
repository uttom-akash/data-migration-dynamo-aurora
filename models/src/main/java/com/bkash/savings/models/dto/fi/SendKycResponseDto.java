package com.bkash.savings.models.dto.fi;

import lombok.Builder;

@Builder
public record SendKycResponseDto(String accountId, String correlationId, String startDate, String endDate,
                                 String maturityDate) {
}
