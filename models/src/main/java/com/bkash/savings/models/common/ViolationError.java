package com.bkash.savings.models.common;

import java.io.Serializable;

public record ViolationError(String field, String message) implements Serializable {
}