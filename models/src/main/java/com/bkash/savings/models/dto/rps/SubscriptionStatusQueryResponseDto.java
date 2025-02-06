package com.bkash.savings.models.dto.rps;

import com.bkash.savings.models.dto.subscription.RPPSubscriptionStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class SubscriptionStatusQueryResponseDto extends RppProxyResponseDto {

    private Long id;
    private String createdAt;
    private String modifiedAt;
    private String subscriptionRequestId;
    private Long requesterId;
    private Long serviceId;
    private String paymentType;
    private String subscriptionType;
    private String amountQueryUrl;
    private Double amount;
    private Double firstPaymentAmount;
    private boolean maxCapRequired;
    private Double maxCapAmount;
    private String frequency;
    private String startDate;
    private String expiryDate;
    private Long merchantId;
    private String payerType;
    private String payer;
    private String currency;
    private String nextPaymentDate;
    private RPPSubscriptionStatus status;
    private String subscriptionReference;
    private Object extraParams;
    private String cancelledBy;
    private String cancelledTime;
    private boolean enabled;
    private boolean expired;
    private String rrule;
    private boolean active;

    @JsonIgnore
    public boolean isSubscriptionCancelled() {
        return status.equals(RPPSubscriptionStatus.CANCELLED) || status.equals(RPPSubscriptionStatus.EXPIRED);
    }

    @JsonIgnore
    public boolean isSubscriptionActive() {
        return status.equals(RPPSubscriptionStatus.SUCCEEDED);
    }
}
