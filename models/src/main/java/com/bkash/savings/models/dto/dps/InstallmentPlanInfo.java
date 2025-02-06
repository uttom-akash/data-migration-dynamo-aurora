package com.bkash.savings.models.dto.dps;

import com.bkash.savings.models.postgres.transaction.TransactionType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstallmentPlanInfo implements Serializable {

    private String instalmentPayDate; // yyyyMMddHHmmss

    // this field is only set for MISSED_DEPOSIT, also known as IRREGULAR_PAID
    // why don't we set this for normal or regular payment?
    private String installmentReceivedDate;

    private String instalmentStatus;

    private String amount;

    // Below fields will have value for V2 type organization, and when instalmentStatus is "P" / "IP"
    private String bkTransactionId;

    private String bkTransactionDate; // yyyyMMddHHmmss

    private String fiTransactionId;

    private String fiTransactionDate; // yyyyMMddHHmmss

    private Integer fiInstallmentPlanNumber;

    private TransactionType transactionType;
}
