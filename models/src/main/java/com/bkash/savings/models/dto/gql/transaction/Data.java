package com.bkash.savings.models.dto.gql.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Data(

	@JsonProperty("getTransaction")
	GetTransaction getTransaction
) {
}