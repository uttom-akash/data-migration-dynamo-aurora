package com.bkash.savings.models.dto.gql.identity;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GetIdentityInfo(
        @JsonProperty("identityId")
        String identityId
) {
}