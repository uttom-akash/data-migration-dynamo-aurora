package com.bkash.savings.models.postgres.rps;

public enum RpsStatus {
    SUBSCRIPTION_INITIATED,
    ACTIVE,
    SUBSCRIPTION_FAILED,
    CANCEL_ONGOING,
    CANCELLED
}
