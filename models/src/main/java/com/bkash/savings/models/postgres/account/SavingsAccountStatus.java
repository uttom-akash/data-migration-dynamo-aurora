package com.bkash.savings.models.postgres.account;

public enum SavingsAccountStatus {

    INACTIVE(0, "Inactive"),
    INIT(1, "Init"),
    IN_PROGRESS(6, "In Progress"),
    ACTIVE(2, "Active"),
    MATURED(3, "Matured"),
    DISBURSED(4, "Disbursed"),
    FAILED(5, "Failed"),
    CANCELLED(8, "Cancelled");

    private final int value;
    private final String description;

    SavingsAccountStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public static SavingsAccountStatus bankGivenStatusToEnum(String accountStatus) {
        return SavingsAccountStatus.valueOf(accountStatus.toUpperCase());
    }

    public int value() {
        return value;
    }

    public String description() {
        return description;
    }
}