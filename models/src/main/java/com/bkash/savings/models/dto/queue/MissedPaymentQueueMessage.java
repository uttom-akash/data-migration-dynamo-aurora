package com.bkash.savings.models.dto.queue;

import lombok.Builder;

@Builder(toBuilder = true)
public record MissedPaymentQueueMessage(
        InstallmentPayQueueTarget target,
        String savingsId,
        String walletNumber,
        String dueDate,
        String amount,
        String transactionId,
        String paymentId,
        String traceId
) {
    public MissedPaymentQueueMessage {
        transactionId = transactionId == null ? "" : transactionId;
    }

    public void setTransactionId(String trxId) {
        this.toBuilder().transactionId(trxId).build();
    }

    public void setTarget(InstallmentPayQueueTarget installmentPayQueueTarget) {
        this.toBuilder().target(installmentPayQueueTarget).build();
    }
}