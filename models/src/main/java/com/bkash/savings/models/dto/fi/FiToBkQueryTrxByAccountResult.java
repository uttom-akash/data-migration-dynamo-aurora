package com.bkash.savings.models.dto.fi;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiToBkQueryTrxByAccountResult extends FiBaseResult {

	private FiToBkQueryTrxByAccountResultData data;
	
	public record FiToBkQueryTrxByAccountResultData(@JsonProperty("allPaymentList") List<FiToBkQueryTrxInfo> transactions) {}
}
