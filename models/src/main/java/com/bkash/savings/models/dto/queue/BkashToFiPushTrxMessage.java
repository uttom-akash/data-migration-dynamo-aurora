package com.bkash.savings.models.dto.queue;

import com.bkash.savings.models.dto.fi.BkToFiPushTrxRequest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BkashToFiPushTrxMessage {
	private String traceId;
	private String orgCode;
	private String url;
	private BkToFiPushTrxRequest requestPayload;
}
