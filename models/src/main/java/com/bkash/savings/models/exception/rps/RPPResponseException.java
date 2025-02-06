package com.bkash.savings.models.exception.rps;

import com.bkash.savings.models.common.ApiResponseStatus;
import com.bkash.savings.models.exception.SavingsException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RPPResponseException extends SavingsException {

    public RPPResponseException(String code, String message) {
        super(code, message);
    }
    public RPPResponseException(ApiResponseStatus apiResponseStatus) {
        super(apiResponseStatus.code(), apiResponseStatus.message());
    }
}
