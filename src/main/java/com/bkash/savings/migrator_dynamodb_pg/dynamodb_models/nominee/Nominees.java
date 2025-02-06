package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.nominee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Nominees {
    private String walletId;
    private String walletNumber;
    private List<Nominee> nomineeList;
}
