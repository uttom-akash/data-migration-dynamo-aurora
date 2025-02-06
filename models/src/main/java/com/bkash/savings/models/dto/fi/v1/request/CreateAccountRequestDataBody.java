package com.bkash.savings.models.dto.fi.v1.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateAccountRequestDataBody extends RequestBody {
    @NotBlank(message = "Wallet no cannot be blank")
    private String walletNo;

    @NotBlank(message = "FI account id cannot be blank")
    private String accountId;

    @NotBlank(message = "FI account no cannot be blank")
    private String accountNo;

    @NotBlank(message = "Start date cannot be blank")
    private String startDate;

    @NotBlank(message = "End date cannot be blank")
    private String endDate;

    @NotBlank(message = "Maturity date cannot be blank")
    private String maturityDate;
}
