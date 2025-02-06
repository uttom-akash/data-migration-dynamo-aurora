package com.bkash.savings.models.dto.fi.v1.response;

import com.bkash.savings.common.utils.DateTimeUtils;
import com.bkash.savings.models.common.ApiResponseStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.f4b6a3.uuid.UuidCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PaymentRefundResponseDTO extends ResponseBody {

    public final static String REFUND_REQUEST_SUBMITTED_MESSAGE = "Your refund request has been submitted for processing.";

    @JsonIgnore
    private boolean rppRefundRequired;

    public static PaymentRefundResponseDTO success() {
        return PaymentRefundResponseDTO.builder()
                .result(PaymentRefundResponseBody.builder()
                        .resultCode(ApiResponseStatus.REFUNDED.code())
                        .resultStatus(FiResponseStatus.SUCCESSFUL.getStatus())
                        .resultMessage(REFUND_REQUEST_SUBMITTED_MESSAGE)
                        .build())
                .timestamp(String.valueOf(DateTimeUtils.getCurrentUnixTimestamp()))
                .responseId(UUID.randomUUID().toString())
                .correlationId(UUID.randomUUID().toString())
                .build();
    }

    public static PaymentRefundResponseDTO failed(String message) {
        return PaymentRefundResponseDTO.builder()
                .result(PaymentRefundResponseBody.builder()
                        .resultCode(ApiResponseStatus.NOT_REFUNDABLE.code())
                        .resultStatus(FiResponseStatus.FAILED.getStatus())
                        .resultMessage(message)
                        .build())
                .timestamp(String.valueOf(DateTimeUtils.getCurrentUnixTimestamp()))
                .responseId(UUID.randomUUID().toString())
                .correlationId(UUID.randomUUID().toString())
                .build();
    }
}
