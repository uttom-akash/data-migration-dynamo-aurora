package com.bkash.savings.models.exception.cps;

import com.bkash.savings.models.common.ApiResponseStatus;

public class CpsPendingException extends CpsException {
    private static final long serialVersionUID = -2519929089529580115L;

	public CpsPendingException(ApiResponseStatus apiResponseStatus) {
        super(apiResponseStatus);
    }

    public CpsPendingException(String code, String message) {
        super(code, message);
    }
}
