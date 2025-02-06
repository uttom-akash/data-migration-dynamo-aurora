package com.bkash.savings.models.exception.cps;

import com.bkash.savings.models.common.ApiResponseStatus;

public class CpsPaymentException extends CpsException {
    private static final long serialVersionUID = 2965942418104320304L;

	public CpsPaymentException(ApiResponseStatus apiResponseStatus) {
        super(apiResponseStatus);
    }

    public CpsPaymentException(String code, String message) {
        super(code, message);
    }
}