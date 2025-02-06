package com.bkash.savings.models.exception.rps;

import com.bkash.savings.models.common.ApiResponseStatus;
import com.bkash.savings.models.exception.SavingsException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RppSubscriptionAlreadyCancelledException extends SavingsException {

    public RppSubscriptionAlreadyCancelledException(String code, String message) {
        super(code, message);
    }

    public RppSubscriptionAlreadyCancelledException(ApiResponseStatus apiResponseStatus) {
        super(apiResponseStatus);
    }
}
