package com.bkash.savings.models.exception.rps;

import com.bkash.savings.models.exception.SavingsException;

public class RppRefundRequestException extends SavingsException {

    public RppRefundRequestException(String code, String message) {
        super(code, message);
    }
}
