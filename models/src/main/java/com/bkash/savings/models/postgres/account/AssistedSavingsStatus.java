package com.bkash.savings.models.postgres.account;

public enum AssistedSavingsStatus {
    PENDING(0, "Pending"),
    IN_PROGRESS(1, "In Progress"),
    OPENED(2, "Opened"),

    FAILED(3, "Failed"),
    EXPIRED(4, "Expired"),
    DECLINED(5, "Declined");

    private final int value;
    private final String description;

    AssistedSavingsStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int value() {
        return value;
    }

    public String description() {
        return description;
    }

}