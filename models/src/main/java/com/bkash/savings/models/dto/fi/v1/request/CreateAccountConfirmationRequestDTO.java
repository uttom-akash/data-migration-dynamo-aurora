package com.bkash.savings.models.dto.fi.v1.request;

import jakarta.validation.Valid;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public class CreateAccountConfirmationRequestDTO extends RequestBody {

    @Valid
    private CreateAccountRequestDataBody data;


    private String signature;
}
