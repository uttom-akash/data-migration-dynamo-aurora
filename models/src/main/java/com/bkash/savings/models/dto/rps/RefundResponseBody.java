package com.bkash.savings.models.dto.rps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class RefundResponseBody extends WebhookRequestBody {

    private Long paymentId;

    private String paymentStatus;

    private String reverseTrxId;

    private Double refundedAmount;

    private LocalDateTime reversTrxDate;

    private Boolean firstPayment;

    private boolean requestTimeOut;

}
