package com.bkash.savings.models.postgres.account;

/**
 * Represents the various states of a Financial Institution (FI) request status. This enum tracks the status of an
 * account on FI end.
 */
public enum FIStatus {
    /**
     * The request has not landed on FI
     */
    NOT_INITIATED,

    /**
     * The request has landed on FI but FI has not responded
     */
    INITIATED,

    /**
     * Account creation process is pending on FI end
     */
    PENDING,

    /**
     * Account is active on FI end
     */
    ACTIVE,

    /**
     * The FI has closed the account
     */
    CLOSED,

    /**
     * Account creation has failed on FI end due to some technical issues
     */
    FAILED
}