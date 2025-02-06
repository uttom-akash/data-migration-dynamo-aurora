package com.bkash.savings.models.dto.rps;

import com.bkash.savings.models.dto.subscription.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SubscriptionCreateRequestDto {

    private boolean firstPaymentIncludedInCycle;

    private boolean maxCapRequired;

    private int serviceId;

    private double amount;

    private Double firstPaymentAmount;

    private double maxCapAmount;

    private String startDate;

    private String expiryDate;

    private String subscriptionRequestId;

    private String merchantShortCode;

    private String payer;

    private String signature;

    private String redirectUrl;

    private String amountQueryUrl;

    private String subscriptionReference;

    private Currency currency;

    private SubscriptionFrequency frequency;

    private SubscriptionPayerType payerType;

    private SubscriptionPaymentType paymentType;

    private SubscriptionType subscriptionType;
}
