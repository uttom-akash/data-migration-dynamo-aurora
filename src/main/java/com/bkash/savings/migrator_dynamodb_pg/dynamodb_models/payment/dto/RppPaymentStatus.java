package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.payment.dto;

public enum RppPaymentStatus {

    INITIALIZED,
    PROCESSING_PAYMENT,
    PROCESSING_REFUND,
    SUCCEEDED_PAYMENT,
    SUCCEEDED_REFUND,
    FAILED_PAYMENT,
    FAILED_REFUND,
    RE_SUCCEEDED_PAYMENT,
    RE_SUCCEEDED_REFUND,
    RE_FAILED_PAYMENT,
    RE_FAILED_REFUND,
    RE_NOT_FOUND_PAYMENT,
    RE_NOT_FOUND_REFUND
}