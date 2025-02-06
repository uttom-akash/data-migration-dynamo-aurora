package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ViolationError implements Serializable {
    private String field;
    private String message;
}
