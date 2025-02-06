package com.bkash.savings.models.dto.gql.transactionSummary;

import java.io.Serializable;


public record Transaction(String reasonType, String transactionType, Integer transCount,
                          Double transCommission) implements Serializable {
}