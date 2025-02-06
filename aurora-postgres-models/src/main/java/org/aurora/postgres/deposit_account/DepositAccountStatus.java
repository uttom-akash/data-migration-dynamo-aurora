package org.aurora.postgres.deposit_account;

public enum DepositAccountStatus {
    INACTIVE,
    INIT,
    IN_PROGRESS,
    ACTIVE,
    MATURED,
    DISBURSED,
    FAILED,
    CANCELLED
}