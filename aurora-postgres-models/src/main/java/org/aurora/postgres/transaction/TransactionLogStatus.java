package org.aurora.postgres.transaction;

public enum TransactionLogStatus {
    INITIATED,
    SUCCESSFUL,
    FAILED,
    PENDING
}
