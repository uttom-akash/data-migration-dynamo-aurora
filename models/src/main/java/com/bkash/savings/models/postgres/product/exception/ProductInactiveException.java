package com.bkash.savings.models.postgres.product.exception;

public class ProductInactiveException extends ProductValidationException {

        public ProductInactiveException(String code, String message) {
            super(code, message);
        }
}
