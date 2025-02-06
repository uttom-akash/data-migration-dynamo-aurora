package com.bkash.savings.models.exception.fi;

import com.bkash.savings.models.exception.SavingsException;

import static com.bkash.savings.models.common.ApiResponseStatus.SUBSCRIPTION_ALREADY_ACTIVE;

public class AccountAlreadyActiveException extends SavingsException {
    public AccountAlreadyActiveException() {
        super(SUBSCRIPTION_ALREADY_ACTIVE.code(), SUBSCRIPTION_ALREADY_ACTIVE.message());
    }
}
