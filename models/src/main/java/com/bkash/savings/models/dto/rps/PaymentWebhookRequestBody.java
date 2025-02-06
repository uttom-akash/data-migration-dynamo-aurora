package com.bkash.savings.models.dto.rps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentWebhookRequestBody extends WebhookRequestBody {

    private long paymentId;

    private String paymentStatus;

    private boolean firstPayment;

    private String trxId;

    private String amount;

    private LocalDate dueDate;

    private LocalDate nextPaymentDate;

    private LocalDateTime trxDate;

}
