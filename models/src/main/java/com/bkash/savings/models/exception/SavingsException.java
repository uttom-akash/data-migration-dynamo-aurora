package com.bkash.savings.models.exception;

import lombok.Getter;

import java.io.Serial;

import com.bkash.savings.models.common.ApiResponseStatus;

@Getter
public class SavingsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String code;

    public SavingsException(ApiResponseStatus apiResponseStatus) {
        super(apiResponseStatus.message());
        this.code = apiResponseStatus.code();
    }

    public SavingsException(String code, String message) {
        super(message);
        this.code = code;
    }
}
