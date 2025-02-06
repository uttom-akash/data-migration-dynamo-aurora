package com.bkash.savings.models.exception.cps;

import com.bkash.savings.models.common.ApiResponseStatus;
import com.bkash.savings.models.exception.SavingsException;

public class CpsException extends SavingsException {
    private static final long serialVersionUID = 5697044577716466811L;

	public CpsException(ApiResponseStatus apiResponseStatus) {
        super(apiResponseStatus);
    }

    public CpsException(String code, String message) {
        super(code, message);
    }
}