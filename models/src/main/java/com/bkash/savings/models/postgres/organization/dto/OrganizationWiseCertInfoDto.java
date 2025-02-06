package com.bkash.savings.models.postgres.organization.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link com.bkash.savings.models.postgres.organization.OrganizationEntity}
 */
@Builder
public record OrganizationWiseCertInfoDto(
        @NotBlank(message = "Organization code cannot be empty") String organizationCode,
        @NotBlank(message = "Certificate open password cannot be empty") String privateKeyStorePassword,
        @NotBlank(message = "Certificate alias name cannot be empty") String privateCertAliasName,
        @NotBlank(message = "Certificate path of s3 cannot be empty") String privateCertPath) implements Serializable {
}