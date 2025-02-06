package com.bkash.savings.models.postgres.product.exception;

import com.bkash.savings.models.exception.SavingsException;

public class ProductValidationException extends SavingsException {

    public ProductValidationException(String code, String message) {
        super(code, message);
    }
}
