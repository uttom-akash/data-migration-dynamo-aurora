package com.bkash.savings.models.common;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class CommonResponseDTO implements Serializable {

    @Builder.Default
    private String code = ApiResponseStatus.SUCCESSFUL.code();

    @Builder.Default
    private String message = ApiResponseStatus.SUCCESSFUL.message();
}
