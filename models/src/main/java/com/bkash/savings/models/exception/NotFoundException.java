package com.bkash.savings.models.exception;


import com.bkash.savings.models.common.ApiResponseStatus;

public class NotFoundException extends SavingsException {

    public NotFoundException(String message) {
        super(ApiResponseStatus.NOT_FOUND.code(), message);
    }

    public NotFoundException(ApiResponseStatus status) {
        super(status.code(), status.message());
    }

    public NotFoundException(String code, String message) {
        super(code, message);
    }
}
