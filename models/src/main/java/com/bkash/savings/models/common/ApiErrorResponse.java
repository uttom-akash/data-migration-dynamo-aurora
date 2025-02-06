package com.bkash.savings.models.common;

import lombok.Builder;

@Builder
public record ApiErrorResponse(
        String code,
        String message,
        ResponseStatus responseStatus
) {
    public ApiErrorResponse() {
        this(ApiResponseStatus.SUCCESSFUL.code(), "", null);
    }
}