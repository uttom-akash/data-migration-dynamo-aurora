package com.bkash.savings.models.dto.rps;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RppProxyResponseDto {

    private String errorCode;

    private String errorMessageEn;

    private String requestTimeOut;
    
    @JsonIgnore
    public boolean hasError() {
    	return StringUtils.isNoneBlank(errorCode);
    }
}
