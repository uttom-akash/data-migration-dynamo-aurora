package com.bkash.savings.models.dto.fi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiToBkPullTrxRequest {

	private String requestId;
	
	private String timestamp;
	
	/** yyyyMMdd */
	private String queryDate;
	
	private String orgCode;
}
