package com.bkash.savings.models.exception;

import com.bkash.savings.models.common.ApiResponseStatus;

public class TransactionSyncException extends SavingsException {
    public TransactionSyncException(String message) {
        super(ApiResponseStatus.NOT_FOUND.code(), message);
    }

    public TransactionSyncException(ApiResponseStatus status) {
        super(status.code(), status.message());
    }

    public TransactionSyncException(String code, String message) {
        super(code, message);
    }
}
