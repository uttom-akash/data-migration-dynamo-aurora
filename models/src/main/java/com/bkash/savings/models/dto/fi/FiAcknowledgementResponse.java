package com.bkash.savings.models.dto.fi;

import java.time.Instant;

import com.bkash.savings.models.common.ApiResponseStatus;
import com.bkash.savings.models.util.UniqueIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FiAcknowledgementResponse extends FiBaseResponse {
	
	private FiAcknowledgementResult result;
	
	public record FiAcknowledgementResult(String resultCode, String resultStatus, String resultMessage) {}
	
	@JsonIgnore
	public boolean isSuccessful() {
		return this.getResult().resultStatus().equals("S");
	}
	
	public static FiAcknowledgementResponse successful(String correlationId) {
		var res = build(correlationId);
		res.setResult(new FiAcknowledgementResult(ApiResponseStatus.SUCCESSFUL.code(), "S", "Request processed Successfully."));
		return res;
	}
	
	public static FiAcknowledgementResponse error(String correlationId, String errorMessage) {
		var res = build(correlationId);
		res.setResult(new FiAcknowledgementResult(ApiResponseStatus.UNSUCCESSFUL.code(), "F", errorMessage));
		return res;
	}
	
	private static FiAcknowledgementResponse build(String correlationId) {
		return FiAcknowledgementResponse.builder()
				.responseId(UniqueIdGenerator.getRequestId())
				.correlationId(correlationId)
				.timestamp(String.valueOf(Instant.now().getEpochSecond()))
				.build();
	}
}
