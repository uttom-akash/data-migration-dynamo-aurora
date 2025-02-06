package com.bkash.savings.models.dto.rps;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WebhookRequestBody {

    private long subscriptionId;

    private String subscriptionRequestId;

    private String message;

    private LocalDateTime timeStamp;

}
