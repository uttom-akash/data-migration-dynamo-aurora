package com.bkash.savings.models.dto.fi;

import com.bkash.savings.models.postgres.transaction.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class FiCancelAccountRequest extends FiBaseRequest {

    private FiCancelAccountRequestData data;

    @Data
    @EqualsAndHashCode
    @ToString
    public static class FiCancelAccountRequestData {
        private String walletNo;
        @JsonProperty("accountNo")
        private String fiAccountNo;
        @JsonProperty("accountId")
        private String fiAccountID;
        private String reason;
        private String disbursementAmount;
        private String totalDipositAmount;
        private String accumulatedInterest;
        private String taxAmount;
        private String interestRate;
        private String instalmentPercentage;
        private String lostOfInterestEarning;
        private String closingFiTransactionId;
        private String closingFiTransactionDate;
        @JsonProperty("allPaymentList")
        private List<ClosingTransactionInfo> transactions;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @SuperBuilder
    public static class ClosingTransactionInfo extends XTransactionInfo {
        @JsonProperty("trxDate")
        private String bKashTransactionDate;
        @JsonProperty("transactionType")
        private TransactionType bKashTransactionType;
        @JsonProperty("bkInstallmentPlanNumber")
        private String bKashInstallmentPlanNumber;
    }
}
