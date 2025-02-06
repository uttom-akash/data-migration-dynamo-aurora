package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.rpp.enums;

public enum RppWebHookResponseStatus {

    INITIALIZED("INITIALIZED"),
    CANCELLED("CANCELLED"),
    SUCCEEDED("SUCCEEDED"),
    FAILED("FAILED"),
    SUCCEEDED_PAYMENT("SUCCEEDED_PAYMENT"),
    FAILED_PAYMENT("FAILED_PAYMENT"),
    SUCCEEDED_REFUND("SUCCEEDED_REFUND"),
    FAILED_REFUND("FAILED_REFUND");

    private final String value;


    RppWebHookResponseStatus(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
