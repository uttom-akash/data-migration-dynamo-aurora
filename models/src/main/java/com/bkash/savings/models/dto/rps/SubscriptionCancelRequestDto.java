package com.bkash.savings.models.dto.rps;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionCancelRequestDto {

    private long rppSubscriptionId;

    private String reason;
}
