package com.bkash.savings.models.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import com.bkash.savings.models.dto.queue.SavingsQueue;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ConfigurationProperties(prefix = "queue")
@Validated
@Getter
@Setter
@ToString
public class QueueProperties {
    @Valid
    @NotBlank
    private String account;
    @Valid
    @NotBlank
    private String assistedCommission;
    @Valid
    @NotBlank
    private String installmentPay;
    @Valid
    @NotBlank
    private String schedulerFailedPendingMaturity;
    @Valid
    @NotBlank
    private String accountCancellation;
    @Valid
    @NotBlank
    private String bkToFiPushTrxQueue;
    @Valid
    @NotBlank
    private String savingsRpsInternalQueue;
    @Valid
    @NotBlank
    private String savingsRpsWebhookConfirmationQueue;

    public String getQueueName(SavingsQueue queue) {
        return switch (queue) {
            case ACCOUNT_QUEUE -> account;
            case ASSISTED_COMMISSION_QUEUE -> assistedCommission;
            case MISS_PAYMENT_QUEUE -> installmentPay;
            case PENDING_MATURITY_QUEUE -> schedulerFailedPendingMaturity;
            case CANCELLATION_QUEUE -> accountCancellation;
            case BK_TO_FI_PUSH_TRX_QUEUE -> bkToFiPushTrxQueue;
            case RPS_INTERNAL_QUEUE -> savingsRpsInternalQueue;
            case RPS_WEBHOOK_CONFIRMATION_QUEUE -> savingsRpsWebhookConfirmationQueue;
        };
    }
}
