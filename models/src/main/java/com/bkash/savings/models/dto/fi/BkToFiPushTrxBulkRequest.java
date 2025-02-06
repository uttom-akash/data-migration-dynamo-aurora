package com.bkash.savings.models.dto.fi;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true) // To handle equals and hashCode for subclasses
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BkToFiPushTrxBulkRequest extends FiBaseRequest {
	
	@JsonIgnore
	private String url;
	
	@JsonProperty("data")
	private List<BkToFiPushTrxRequest> transactions;
	
	public BkToFiPushTrxBulkRequest addTransaction(BkToFiPushTrxRequest transaction) {
		if (transactions == null) transactions = new ArrayList<>();
		transactions.add(transaction);
		return this;
	}

}
