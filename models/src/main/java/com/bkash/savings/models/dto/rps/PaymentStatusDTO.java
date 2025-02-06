package com.bkash.savings.models.dto.rps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentStatusDTO implements Serializable {

    private Long id;

    private Long subscriptionId;

    private String dueDate;

    private String status;

    private String trxId;

    private LocalDateTime trxTime;

    private String amount;

    private String reverseTrxAmount;

    private String reverseTrxId;

    private String reverseTrxTime;

    public boolean isValidPayment() {
        return status.equals(RppPaymentStatus.SUCCEEDED_PAYMENT.name());
    }
}
