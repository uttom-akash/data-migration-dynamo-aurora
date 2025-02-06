package com.bkash.savings.models.dto.fi;

import com.bkash.savings.models.postgres.transaction.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class FiToBkashTransactionInfo extends XTransactionInfo {

    /**
     * yyyyMMddHHmmSS formatted date string of the bKash transaction time
     */
    @JsonProperty("trxDate")
    private String bkashTransactionDate;

    @JsonProperty("bkInstallmentPlanNumber")
    private String bKashPlanNumber;

    private TransactionType transactionType;

    /**
     * yyyyMMddHHmmss Formatted date string. <br>
     * Optional. Only mandatory if the trnsactionType is MISSED_DEPOSIT
     **/
    @JsonProperty("instalmentDueDate")
    private String dueDate;

    /**
     * Optional. Mandatory if the transactionType is DISBURSEMENT
     */
    private String closingFiTransactionId;

    /**
     * Optional. Mandatory if the transactionType is DISBURSEMENT. Format: yyyyMMddHHmmss
     */
    private String closingFiTransactionDate;

    /**
     * Optional. Mandatory if the transaction type is REFUND
     */
    @JsonProperty("originalTrxID")
    private String originalTransactionId;


    /**
     * Get the transaction ID based on the transaction type.
     * If the transaction type is not set, it will return the fiTransactionId.
     * <p>
     * For DISBURSEMENT type transaction, this will return the closingFiTransactionId.
     * </p>
     */
    public static String getFiTrxId(FiToBkashTransactionInfo transactionInfo) {
        if (Objects.isNull(transactionInfo.transactionType)) return transactionInfo.fiTransactionId;
        return switch (transactionInfo.transactionType) {
            case DISBURSEMENT -> transactionInfo.closingFiTransactionId;
            case FIRST_DEPOSIT, REGULAR_DEPOSIT, MISSED_DEPOSIT, REFUND, VOID -> transactionInfo.fiTransactionId;
        };
    }

    /**
     * Get the transaction ID based on the transaction type.
     * If the transaction type is not set, it will return the fiTransactionDate.
     * <p>
     * For DISBURSEMENT type transaction, this will return the closingFiTransactionDate.
     * </p>
     */
    public static String getFiTrxDate(FiToBkashTransactionInfo transactionInfo) {
        if (Objects.isNull(transactionInfo.transactionType)) return transactionInfo.fiTransactionDate;
        return switch (transactionInfo.transactionType) {
            case DISBURSEMENT -> transactionInfo.closingFiTransactionDate;
            case FIRST_DEPOSIT, REGULAR_DEPOSIT, MISSED_DEPOSIT, REFUND, VOID -> transactionInfo.fiTransactionDate;
        };
    }
}
