package com.bkash.savings.models.exception.rps;

import com.bkash.savings.models.exception.SavingsException;

public class RpsException extends SavingsException {

    private String savingsId;

    public RpsException(String code, String message) {
        super(code, message);
    }
    public RpsException(String code, String message, String savingsId) {
        super(code, message);
        this.savingsId = savingsId;
    }


}
