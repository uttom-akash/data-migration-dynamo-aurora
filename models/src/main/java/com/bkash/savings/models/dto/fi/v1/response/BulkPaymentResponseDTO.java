package com.bkash.savings.models.dto.fi.v1.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BulkPaymentResponseDTO {

    private String responseId;

    private String responseMessage;

    private Long timestamp;

    private String queryDate;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<BulkPaymentDataDTO> data;

    private String signature;

    private Object referenceParams;
}
