package com.bkash.savings.models.dto.gql.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TransactionResponse(

	@JsonProperty("data")
	Data data
) {
}