package com.bkash.savings.models.dto.product;

import com.bkash.savings.models.postgres.account.SavingsType;
import com.bkash.savings.models.postgres.organization.OrganizationType;
import com.bkash.savings.models.validators.ValidOrganizationType;
import lombok.Builder;

@Builder
public record ProductSchemesRequest(
        @ValidOrganizationType(message = "Organization type is invalid. Please provide one of the following - REGULAR,ISLAMIC")
        String organizationType,
        String savingsType
) {
    public ProductSchemesRequest() {
        this(OrganizationType.REGULAR.name(), SavingsType.DPS.name());
    }
}