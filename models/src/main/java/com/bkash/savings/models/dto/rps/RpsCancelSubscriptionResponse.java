package com.bkash.savings.models.dto.rps;

import com.bkash.savings.models.postgres.rps.RpsStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RpsCancelSubscriptionResponse {

    private String rpsId;

    private RpsStatus subscriptionStatus;

    private String message;
}
