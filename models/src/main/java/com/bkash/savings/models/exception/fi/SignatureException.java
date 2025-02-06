package com.bkash.savings.models.exception.fi;

import com.bkash.savings.models.common.ApiResponseStatus;
import com.bkash.savings.models.exception.SavingsException;

public class SignatureException extends SavingsException {

    public SignatureException(String message) {
        super(ApiResponseStatus.SIGNATURE_VALIDATION_FAILED.code(), message);
    }
}
