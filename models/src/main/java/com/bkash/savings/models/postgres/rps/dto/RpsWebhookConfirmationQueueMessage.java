package com.bkash.savings.models.postgres.rps.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class RpsWebhookConfirmationQueueMessage {
    private long paymentId;

    private String paymentStatus;

    private boolean firstPayment;

    private String trxId;

    private String amount;

    private LocalDate dueDate;

    private LocalDate nextPaymentDate;

    private LocalDateTime trxDate;

    private String traceId;

    /**
     * This field is not given in the webhook, but TMS needs it. So, RPS will set them before sending to queue.
     */
    @JsonIgnore
    private String savingId;

    /**
     * This field is not given in the webhook, but TMS needs it. So, RPS will set them before sending to queue.
     * <p>
     * Context: </br>
     * With the information provided by the RPP webhook, if RPS just simply propagate that to TMS via queue, TMS
     * will not have enough information to identify the saving account.
     * </p>
     */
    private String walletId;
}
