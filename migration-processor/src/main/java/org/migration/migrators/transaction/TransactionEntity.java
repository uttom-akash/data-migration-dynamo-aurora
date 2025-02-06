package org.migration.migrators.transaction;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.aurora.postgres.transaction.TransactionLog;
import org.aurora.postgres.transaction.DepositTransaction;

import java.util.List;

@Data
@Builder
@Getter
@Setter
public class TransactionEntity {
    private List<DepositTransaction> dpsTransactionEntity;
    private TransactionLog cpsTransactionEntity;
}
