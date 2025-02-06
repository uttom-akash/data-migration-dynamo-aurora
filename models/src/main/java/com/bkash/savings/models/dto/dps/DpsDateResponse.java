package com.bkash.savings.models.dto.dps;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record DpsDateResponse(
        LocalDate startDate,
        LocalDate cycleStartDate,
        LocalDate endDate,
        LocalDate maturityDate
) {
}
