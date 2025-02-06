package com.bkash.savings.models.common;

import com.bkash.savings.models.util.Constants;
import lombok.Builder;
import org.slf4j.MDC;

import java.util.List;

@Builder(toBuilder = true)
public record ResponseStatus(
        boolean success,
        String code,
        String message,
        String messageBn,
        List<ViolationError> violationErrors,
        String responseTime,
        String traceId) {
    public ResponseStatus(Boolean success) {
        this(success, success ? ApiResponseStatus.SUCCESSFUL.code() : ApiResponseStatus.UNSUCCESSFUL.code(),
                success ? ApiResponseStatus.SUCCESSFUL.message() : ApiResponseStatus.UNSUCCESSFUL.message(), null,
                null, null,
                MDC.get(Constants.TRACE_ID));
    }
}
