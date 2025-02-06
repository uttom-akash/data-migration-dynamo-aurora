package com.bkash.savings.models.postgres.nominee.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder(toBuilder = true)
public record NomineeInfoForCustomerDashboard(
        @NotBlank String id, // used in details API
        @NotBlank(message = "NID number of the nominee can not be empty") String nidNumber,
        @NotBlank(message = "Birth date of the nominee can not be empty") String dob,
        @NotBlank(message = "Nominee's relation with the account holder is required") String relation
) {
}