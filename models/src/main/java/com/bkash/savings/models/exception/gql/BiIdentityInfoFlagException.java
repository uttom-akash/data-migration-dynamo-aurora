package com.bkash.savings.models.exception.gql;


import com.bkash.savings.models.common.ApiResponseStatus;

public class BiIdentityInfoFlagException extends BiException {
    public BiIdentityInfoFlagException(ApiResponseStatus apiResponseStatus) {
        super(apiResponseStatus);
    }

    public BiIdentityInfoFlagException(String code, String message) {
        super(code, message);
    }
}
