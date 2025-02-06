package com.bkash.savings.models.exception.fi;

import com.bkash.savings.models.common.ApiResponseStatus;
import com.bkash.savings.models.exception.SavingsException;

public class OrganizationInactiveException extends SavingsException {

    public OrganizationInactiveException(ApiResponseStatus apiResponseStatus) {
        super(apiResponseStatus);
    }

    public OrganizationInactiveException(String code, String message) {
        super(code, message);
    }
}
