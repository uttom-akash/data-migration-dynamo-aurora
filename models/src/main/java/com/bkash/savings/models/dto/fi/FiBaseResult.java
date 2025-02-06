package com.bkash.savings.models.dto.fi;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiBaseResult {
	protected String resultCode;
	protected String resultStatus;
	protected String resultMessage;
	
	@JsonIgnore
	public boolean isSuccessful() {
		return StringUtils.isNoneBlank(resultMessage) && resultStatus.equals("S");
	}
}
