package com.bkash.savings.models.dto.fi.v1.request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubscriptionCancelRequestDTO extends RequestBody {

    @Valid
    private FiUnsubscriptionRequestDTO data;

    @NotBlank(message = "Signature cannot be empty")
    private String signature;

}
