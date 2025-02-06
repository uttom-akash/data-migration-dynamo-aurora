package com.bkash.savings.models.dto.dps;

import com.bkash.savings.models.postgres.product.Term;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder(toBuilder = true)
public record DpsDateRequest(
        @NotNull LocalDate startDate, // yyyy-MM-dd
        @Nullable LocalDate cycleStartDate, // yyyy-MM-dd
        @NotNull Term term,
        @NotNull int tenure // in months
) {
}