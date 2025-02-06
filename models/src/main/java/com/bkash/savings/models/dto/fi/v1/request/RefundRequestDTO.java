package com.bkash.savings.models.dto.fi.v1.request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RefundRequestDTO extends RequestBody {

    @Valid
    private RefundRequestBody data;

    @NotBlank(message = "Signature cannot be empty")
    private String signature;
}
