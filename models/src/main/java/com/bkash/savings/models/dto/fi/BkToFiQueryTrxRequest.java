package com.bkash.savings.models.dto.fi;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class BkToFiQueryTrxRequest extends FiBaseRequest {
	
	private String url;

	private BkToFiQueryTrxData data;
	
	public record BkToFiQueryTrxData(List<String> transactionIds) {}
}
