package com.bkash.savings.models.dto.gql.identity;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Data(

	@JsonProperty("getIdentityInfo")
	GetIdentityInfo getIdentityInfo
) {
}