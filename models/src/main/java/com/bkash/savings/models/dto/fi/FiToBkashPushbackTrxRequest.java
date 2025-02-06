package com.bkash.savings.models.dto.fi;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class FiToBkashPushbackTrxRequest extends FiBaseRequest {

	@JsonProperty("data")
	private List<FiToBkashTransactionInfo> transactions;
	
	public FiToBkashPushbackTrxRequest addTransaction(FiToBkashTransactionInfo transaction) {
		if (transactions == null) transactions = new ArrayList<>();
		transactions.add(transaction);
		return this;
	}
}
