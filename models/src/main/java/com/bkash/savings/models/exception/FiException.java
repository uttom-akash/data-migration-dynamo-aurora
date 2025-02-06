package com.bkash.savings.models.exception;


import com.bkash.savings.models.common.ApiResponseStatus;

public class FiException extends SavingsException {

    public FiException(String code, String message) {
        super(code, message);
    }

    public FiException(ApiResponseStatus apiResponseStatus) {
        super(apiResponseStatus);
    }
}
