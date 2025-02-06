package com.bkash.savings.models.dto.fi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FiBaseRequest {

	protected String requestId;
	
	protected String correlationId;
	
	/** UNIX timestamp string */
	protected String timestamp;
	
	protected String orgCode;
}
