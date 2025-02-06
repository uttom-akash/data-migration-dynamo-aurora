package com.bkash.savings.models.dto.gql.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GetTransaction(

	@JsonProperty("transactionCommission")
	String transactionCommission,

	@JsonProperty("transactionStatus")
	String transactionStatus,

	@JsonProperty("completedTime")
	String completedTime,

	@JsonProperty("transactionAmount")
	String transactionAmount
) {
}