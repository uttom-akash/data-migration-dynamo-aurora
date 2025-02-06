package com.bkash.savings.migrator_dynamodb_pg.migrators.transaction;

import com.bkash.savings.models.postgres.transaction.CpsTransactionEntity;
import com.bkash.savings.models.postgres.transaction.DpsTransactionEntity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Builder
@Getter
@Setter
public class TransactionEntity {
    private List<DpsTransactionEntity> dpsTransactionEntity;
    private CpsTransactionEntity cpsTransactionEntity;
}
