package com.bkash.savings.models.dto.fi.v1.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PaymentRefundResponseBody {

    private String resultCode;

    private String resultStatus;

    private String resultMessage;
}
