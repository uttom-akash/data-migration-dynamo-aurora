package com.bkash.savings.models.postgres.product.exception;

public class ProductNotFoundException extends ProductValidationException {

    public ProductNotFoundException(String code, String message) {
        super(code, message);
    }
}
