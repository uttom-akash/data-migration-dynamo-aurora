package com.bkash.savings.models.dto.fi.v1.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RefundRequestBody extends RequestBody {

    @NotBlank(message = "Account no cannot be empty")
    private String accountNo;

    @NotBlank(message = "Trx Id cannot be empty")
    private String trxId;

    @NotBlank(message = "Amount cannot be empty")
    private String amount;
}
