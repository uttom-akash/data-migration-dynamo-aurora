package com.bkash.savings.models.dto.fi;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FiToBkQueryTrxInfo extends XTransactionInfo {
	
	@JsonProperty("trxDate")
	private String bkashTransactionDate;
}
