package com.bkash.savings.models.exception.assisted;

import com.bkash.savings.models.common.ApiResponseStatus;

public class AssistedSavingsNotFoundException extends AssistedSavingsException {
    private static final long serialVersionUID = 1873003033255619564L;

	public AssistedSavingsNotFoundException(ApiResponseStatus apiResponseStatus) {
        super(apiResponseStatus);
    }

    public AssistedSavingsNotFoundException(String code, String message) {
        super(code, message);
    }
}