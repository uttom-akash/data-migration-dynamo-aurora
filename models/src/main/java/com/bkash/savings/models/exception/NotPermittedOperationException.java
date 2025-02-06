package com.bkash.savings.models.exception;


import com.bkash.savings.models.common.ApiResponseStatus;

public class NotPermittedOperationException extends SavingsException {
    public NotPermittedOperationException() {
        super(ApiResponseStatus.NOT_PERMITTED.code(), ApiResponseStatus.NOT_PERMITTED.message());
    }
    public NotPermittedOperationException(String message) {
        super(ApiResponseStatus.NOT_PERMITTED.code(), message);
    }

    public NotPermittedOperationException(String code, String message) {
        super(code, message);
    }
}
