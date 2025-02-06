package com.bkash.savings.models.dto.fi.v1.response;

import com.bkash.savings.models.dto.fi.v1.request.BulkSubscriptionMaturityRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"error"})
public class BulkMaturityResponseDTO {

    private String responseId;

    private String responseMessage;

    private Long timestamp;

    private String queryDate;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<BulkMaturityResponseDataDTO> data;

    private String signature;

    private Object referenceParams;

    private boolean isError;

    public static BulkMaturityResponseDTO defaultFromRequest(BulkSubscriptionMaturityRequestDTO requestDTO) {
        return BulkMaturityResponseDTO.builder()
                .responseId(requestDTO.getRequestId())
                .queryDate(requestDTO.getQueryDate())
                .timestamp(new Date().getTime())
                .responseMessage("Data fetched successfully")
                .build();
    }

    @JsonIgnore
    public BulkMaturityResponseDTO update(String responseMessage, boolean errorOccurred) {
        this.responseMessage = responseMessage;
        this.isError = errorOccurred;
        this.timestamp = new Date().getTime();

        return this;
    }

    @JsonIgnore
    public BulkMaturityResponseDTO update(String signature, List<BulkMaturityResponseDataDTO> data) {
        this.signature = signature;
        this.data = data;
        this.timestamp = new Date().getTime();

        return this;
    }
}
