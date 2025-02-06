package com.bkash.savings.models.exception.rps;

import com.bkash.savings.models.common.ApiResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RppSubscriptionFailedException extends RppException {

    private String walletNo;

    public RppSubscriptionFailedException(ApiResponseStatus apiResponseStatus) {
        super(apiResponseStatus);
    }
    public RppSubscriptionFailedException(String code, String message) {
        super(code, message);
    }

    public RppSubscriptionFailedException(String code, String message, String walletNo) {
        super(code, message);
        this.walletNo = walletNo;
    }

}
