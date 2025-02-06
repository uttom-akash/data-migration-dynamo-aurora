package com.bkash.savings.models.dto.fi;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FiToBkPullTrxResponse {

	private String responseId;
	
	private String responseMessage;
	
	private String timestamp;
	
	/** yyyyMMdd */
	private String queryDate;
	
	@JsonProperty("data")
	private List<FiToBkPullTrxInfo> transactions;
}
