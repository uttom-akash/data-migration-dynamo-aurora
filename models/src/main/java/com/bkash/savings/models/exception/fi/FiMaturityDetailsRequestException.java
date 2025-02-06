package com.bkash.savings.models.exception.fi;

import com.bkash.savings.models.common.ApiResponseStatus;
import com.bkash.savings.models.exception.SavingsException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiMaturityDetailsRequestException extends SavingsException {

    public FiMaturityDetailsRequestException(ApiResponseStatus apiResponseStatus) {
        super(apiResponseStatus.code(), apiResponseStatus.message());
    }
    public FiMaturityDetailsRequestException(String code, String message) {
        super(code, message);
    }
}
