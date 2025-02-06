package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.common.util;

public enum NotificationEvent {

    ONBOARDING_SUCCESS,
    ONBOARDING_FAILED,
    CANCELLATION_SUCCESSFUL, //done
    DISBURSEMENT_SUCCESSFUL,
    MATURED_READY_TO_BE_DISBURSED,
    CANCELLATION_FAILED, //done
    ASSISTED_REQUEST_SUBMITTED;
}
