package com.bkash.savings.models.dto.fi.v1.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class ResponseBody implements Serializable {
    private String responseId;
    private String correlationId;
    private String timestamp;
    private Object result;
    private Object referenceParams;
}
