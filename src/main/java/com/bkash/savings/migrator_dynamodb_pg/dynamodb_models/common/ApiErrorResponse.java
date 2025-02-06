package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.common;

import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.common.util.ApiResponseStatus;
import com.bkash.savings.models.common.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiErrorResponse implements Serializable {


    @Builder.Default
    private String code = ApiResponseStatus.SUCCESSFUL.code();

    @Builder.Default
    private String message = "";

    private ResponseStatus responseStatus;
}
