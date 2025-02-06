package com.bkash.savings.models.dto.rps;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionCancelResponse {

    private Long subscriptionId;

    private String subscriptionStatus;

    private String message;

    private String instant;

    private boolean isRequestTimeOut;

    private String errorCode;
}
