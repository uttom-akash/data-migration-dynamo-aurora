package com.bkash.savings.models.exception.gql;

import com.bkash.savings.models.common.ApiResponseStatus;
import com.bkash.savings.models.exception.SavingsException;

public class BiException extends SavingsException {
    public BiException(ApiResponseStatus apiResponseStatus) {
        super(apiResponseStatus);
    }

    public BiException(String code, String message) {
        super(code, message);
    }
}
