package com.bkash.savings.models.exception.fi;

import com.bkash.savings.models.exception.SavingsException;

public class CreateAccountCompleteException extends SavingsException {
    public CreateAccountCompleteException(String orgCode, String code, String message) {
        super(code, message);
        this.orgCode = orgCode;
    }

    private final String orgCode;

    public String orgCode() {
        return orgCode;
    }
}
