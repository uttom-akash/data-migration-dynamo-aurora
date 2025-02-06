package com.bkash.savings.models.postgres.transaction;

public enum TransactionType {
    FIRST_DEPOSIT, // orginator conversation id
    MISSED_DEPOSIT, // missed
    REGULAR_DEPOSIT, // rest
    DISBURSEMENT,
    REFUND,
    VOID
}


// Cps transaction
//