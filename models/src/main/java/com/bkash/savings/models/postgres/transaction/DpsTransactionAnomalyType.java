package com.bkash.savings.models.postgres.transaction;

import com.bkash.savings.models.exception.SavingsException;

import static com.bkash.savings.models.common.ApiResponseStatus.TRANSACTION_SYNC_ERROR;

public enum DpsTransactionAnomalyType {
    TRANSACTION_TYPE_MISMATCH, // Transaction type mismatch
    FI_ACCOUNT_ID_MISMATCH, // FI account ID mismatch
    FI_ACCOUNT_NUMBER_MISMATCH, // FI account number mismatch
    BK_TRANSACTION_DATE_MISMATCH, // BK transaction date mismatch
    AMOUNT_MISMATCH, // Amount mismatch,
    SAME_FI_TRANSACTION_ID, // Bank's FI transaction ID is already linked to another CPS ID in our database
    FI_APPROVED_TO_OTHER_STATUS, // Transaction status is approved but the bank wants to change it to other status
    FI_UNWANTED_TO_OTHER_STATUS, // Transaction status is unwanted but the bank wants to change it to other status
    DUE_DATE_MISMATCH, // Due date mismatch
    CANNOT_CHANGE_UNWANTED_TO_APPROVED_AS_REFUND_PROCESSING; // Refund processing cannot be approved
    
    
    
    public static DpsTransactionAnomalyType fromString(String value) {
        for (DpsTransactionAnomalyType type : DpsTransactionAnomalyType.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new SavingsException(TRANSACTION_SYNC_ERROR.code(), "Invalid DPS transaction anomaly type");
    }
}
