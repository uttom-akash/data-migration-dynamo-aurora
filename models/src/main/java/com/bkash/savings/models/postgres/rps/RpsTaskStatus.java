package com.bkash.savings.models.postgres.rps;

public enum RpsTaskStatus {
    NOT_REQUESTED,
    REQUESTED,
    FAILED_TO_CREATE,
    FAILED_AND_DONE,
    ACTIVE,
    CANCELLED,
    TERMINATED,
    INVALID,
    FAILED,
    DISCARDED,
}
