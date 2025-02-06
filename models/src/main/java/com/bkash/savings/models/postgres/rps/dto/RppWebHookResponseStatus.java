package com.bkash.savings.models.postgres.rps.dto;

/**
 * <p><strong>Below existing codebase flow, when mw received a rpp webhook:</strong></p>
 * List of possible statuses for RPP Webhook.
 * <p>
 * When a webhook is received from RPP, if the status is {@code FAILED} then we don't process it. </br>
 * If the status is {@code SUCCEEDED_PAYMENT} then we process it and the db accordingly. </br>
 * In all other cases, we process the webhook by setting payment status to UNKNOWN.
 * </p>
 * <p>
 *     TODO: consider combining these two enums into one. {@link com.bkash.savings.models.dto.rps.RppPaymentStatus} and
 *     this one.
 * </p>
 */
public enum RppWebHookResponseStatus {
    INITIALIZED("INITIALIZED"),
    CANCELLED("CANCELLED"),
    SUCCEEDED("SUCCEEDED"),
    FAILED("FAILED"),
    SUCCEEDED_PAYMENT("SUCCEEDED_PAYMENT"),
    FAILED_PAYMENT("FAILED_PAYMENT"),
    SUCCEEDED_REFUND("SUCCEEDED_REFUND"),
    FAILED_REFUND("FAILED_REFUND");

    private final String value;

    RppWebHookResponseStatus(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
