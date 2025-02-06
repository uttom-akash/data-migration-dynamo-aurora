package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.common;

import com.bkash.savings.models.common.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse implements Serializable {
    private ResponseStatus responseStatus;
}

