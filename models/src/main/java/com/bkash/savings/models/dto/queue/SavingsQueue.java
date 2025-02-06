package com.bkash.savings.models.dto.queue;



import com.bkash.savings.models.postgres.rps.dto.RpsTaskQueueMessage;
import com.bkash.savings.models.postgres.rps.dto.RpsWebhookConfirmationQueueMessage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum SavingsQueue {

    ACCOUNT_QUEUE(AccountQueueMessage.class),
    ASSISTED_COMMISSION_QUEUE(AssistedCommissionQueueMessage.class),
    MISS_PAYMENT_QUEUE(MissedPaymentQueueMessage.class),
    PENDING_MATURITY_QUEUE(PendingMaturityAccountQueueMessage.class),
    CANCELLATION_QUEUE(AccountCancellationQueueMessage.class),
    BK_TO_FI_PUSH_TRX_QUEUE(BkashToFiPushTrxMessage.class),
    RPS_INTERNAL_QUEUE(RpsTaskQueueMessage .class),
    RPS_WEBHOOK_CONFIRMATION_QUEUE(RpsWebhookConfirmationQueueMessage.class);
    

    final Class<?> type;

    SavingsQueue(Class<?> type) {
        this.type = type;
    }

    public Class<?> messageObjectType() {
        return type;
    }

}