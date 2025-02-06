package com.bkash.savings.models.dto.fi.v1.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BulkPaymentRequestDTO {

    @NotBlank(message = "Request ID cannot be empty")
    private String requestId;

    @NotNull(message = "Timestamp cannot be empty")
    private Long timestamp;

    @NotBlank(message = "Query date cannot be empty")
    private String queryDate;

    @NotBlank(message = "Organization code cannot be empty")
    private String orgCode;

    // TODO: ???
//    @JsonIgnore
//    private Map<String, AttributeValue> lastEvaluatedKey;
}
