package com.bkash.savings.models.dto.gql.transactionSummary;

import java.io.Serializable;


public record BiTransactionSummary(BiGetTransactionSummary getTransactionSummary) implements Serializable {
}