package com.bkash.savings.models.exception.gql;


import com.bkash.savings.models.common.ApiResponseStatus;

public class CustomerValidationException extends KycException {

    public CustomerValidationException(ApiResponseStatus apiResponseStatus) {
        super(apiResponseStatus.code(), apiResponseStatus.message());
    }

    public CustomerValidationException(String code, String message) {
        super(code, message);
    }

    public CustomerValidationException(String code, String message, String walletNo) {
        super(code, message, walletNo);
    }
}
