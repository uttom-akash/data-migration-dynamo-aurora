package com.bkash.savings.models.exception.rps;

import com.bkash.savings.models.common.ApiResponseStatus;
import com.bkash.savings.models.exception.SavingsException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RppException extends SavingsException {

    private String walletNo;

    public RppException(ApiResponseStatus apiResponseStatus) {
        super(apiResponseStatus);
    }

    public RppException(String code, String message) {
        super(code, message);
    }

    public RppException(String code, String message, String walletNo) {
        super(code, message);
        this.walletNo = walletNo;
    }

}
