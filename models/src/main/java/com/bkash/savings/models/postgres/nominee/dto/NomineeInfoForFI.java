package com.bkash.savings.models.postgres.nominee.dto;

import com.bkash.savings.models.postgres.nominee.Relation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link com.bkash.savings.models.postgres.nominee.NomineeEntity}
 */
@Builder
public record NomineeInfoForFI(
        @NotBlank(message = "NID number of the nominee can not be empty") String nidNumber,
        @NotNull(message = "Birth date of the nominee can not be empty") String dob,
        @NotNull(message = "Nominee's relation with the account holder is required") Relation relation)
        implements Serializable {
}