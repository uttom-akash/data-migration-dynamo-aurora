package com.bkash.savings.models.dto.fi.v1.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentSignatureDTO {
    String responseMessage;
    String signature;
}
