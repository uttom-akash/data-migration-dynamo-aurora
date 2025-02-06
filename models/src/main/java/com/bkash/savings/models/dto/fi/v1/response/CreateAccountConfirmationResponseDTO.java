package com.bkash.savings.models.dto.fi.v1.response;


import com.bkash.savings.models.common.ApiResponseStatus;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountConfirmationResponseDTO {

    private String responseId;

    private String correlationId;

    private Long timestamp;

    private ResultBody result;

    private Object referenceParams;

    public static CreateAccountConfirmationResponseDTO successfulResponse(String orgCode) {
        String uuid = UUID.randomUUID().toString();
        return CreateAccountConfirmationResponseDTO.builder()
                .responseId(uuid)
                .correlationId(uuid)
                .timestamp(System.currentTimeMillis())
                .result(new ResultBody(ApiResponseStatus.SUCCESSFUL.code(), "S", ApiResponseStatus.SUCCESSFUL.message(), null, orgCode))
                .build();
    }

    public static CreateAccountConfirmationResponseDTO failureResponse(String code, String orgCode, String message) {
        String uuid = UUID.randomUUID().toString();
        return CreateAccountConfirmationResponseDTO.builder()
                .responseId(uuid)
                .correlationId(uuid)
                .timestamp(System.currentTimeMillis())
                .result(new ResultBody(code, "F", message, null, orgCode))
                .build();
    }
}
