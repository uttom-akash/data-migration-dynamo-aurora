package com.bkash.savings.models.dto.dps;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record DpsPlanNumberResponse(
        int planNo,
        LocalDate dueDate // This is the due date that is mapped to the plan number
) {
}
