package com.bkash.savings.models.dto.fi.v1.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FiUnsubscriptionRequestDTO extends RequestBody {

    @NotBlank(message = "Account no cannot be empty")
    private String accountNo;

    @NotBlank(message = "Wallet no cannot be empty")
    private String walletNo;

    @NotBlank(message = "Reason cannot be empty")
    private String reason;

    @JsonIgnore
    private String requestedBy;

}
