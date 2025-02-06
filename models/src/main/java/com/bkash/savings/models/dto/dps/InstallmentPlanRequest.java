package com.bkash.savings.models.dto.dps;

import com.bkash.savings.models.postgres.product.Term;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder(toBuilder = true)
public record InstallmentPlanRequest(
        @NotNull LocalDate startDate,
        @Nullable LocalDate cycleStartDate, // if null, cycleStartDate = startDate + term
        @NotNull Term term,
        @NotNull int tenure
) {
}
