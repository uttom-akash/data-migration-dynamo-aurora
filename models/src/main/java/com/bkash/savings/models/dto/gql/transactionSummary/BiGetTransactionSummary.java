package com.bkash.savings.models.dto.gql.transactionSummary;

import java.io.Serializable;
import java.util.List;

public record BiGetTransactionSummary(
    List<Transaction> today,
    List<Transaction> currMonth,
    List<Transaction> prevMonth,
    List<Transaction> prev2Month
) implements Serializable {
}