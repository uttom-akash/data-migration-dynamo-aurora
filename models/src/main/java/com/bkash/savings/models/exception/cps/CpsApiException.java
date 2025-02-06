package com.bkash.savings.models.exception.cps;

import com.bkash.savings.models.common.ApiResponseStatus;

public class CpsApiException extends CpsException {

    private static final long serialVersionUID = -3443256349107490004L;

	public CpsApiException(ApiResponseStatus apiResponseStatus) {
        super(apiResponseStatus);
    }

    public CpsApiException(String code, String message) {
        super(code, message);
    }
}