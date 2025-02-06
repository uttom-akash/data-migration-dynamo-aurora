package com.bkash.savings.models.dto.fi.v1.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * This is copied from the previous codebase.
 */
@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class FiCommonResponseV1 {
    private String code;
    private String message;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;
}
