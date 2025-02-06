package com.bkash.savings.models.dto.fi;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FiToBkQueryTrxByAccountRequest extends FiBaseRequest {
	
	private FiToBkQueryTrxByAccountRequestData data;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public class FiToBkQueryTrxByAccountRequestData {
		
		@JsonProperty("accountId")
		private List<String> accountIds;
	}
	
}