package com.bkash.savings.models.exception.assisted;

import com.bkash.savings.models.common.ApiResponseStatus;
import com.bkash.savings.models.exception.SavingsException;

public class AssistedSavingsException extends SavingsException {
    private static final long serialVersionUID = -8494691399449231664L;

	public AssistedSavingsException(ApiResponseStatus apiResponseStatus) {
        super(apiResponseStatus);
    }

    public AssistedSavingsException(String code, String message) {
        super(code, message);
    }
}
