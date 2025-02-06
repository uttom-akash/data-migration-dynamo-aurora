package com.bkash.savings.models.dto.rps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionCreateResponseDto extends RppProxyResponseDto {

    private long subscriptionId;

    private double amount;

    private String trxId;

    private String message;

    private String cancelledBy;

    private String  subscriptionStatus;

    private String subscriptionRequestId;

    private LocalDateTime trxDate;

    private LocalDateTime timeStamp;

    private LocalDate nextPaymentDate;
}
