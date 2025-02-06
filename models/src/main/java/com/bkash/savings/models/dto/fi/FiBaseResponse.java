package com.bkash.savings.models.dto.fi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiBaseResponse {

	protected String responseId;
	
	protected String correlationId;
	
	protected String timestamp;
	
}
