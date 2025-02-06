package com.bkash.savings.migrator_dynamodb_pg.migrators.transaction;

import com.bkash.savings.migrator_dynamodb_pg.configs.PostgresTableNames;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.PostgresWriter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TransactionWriter extends PostgresWriter implements ItemWriter<TransactionEntity> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void write(Chunk<? extends TransactionEntity> items) throws Exception {

        for (var transactionEntity : items.getItems()) {
            if(transactionEntity == null) continue;

            if(transactionEntity.getDpsTransactionEntity() !=null) {
                for(var dpsTransactionEntity : transactionEntity.getDpsTransactionEntity()) {
                    dpsTransactionEntity.setId(UUID.randomUUID());
                    merge(dpsTransactionEntity,
                            PostgresTableNames.DPS_TRANSACTION,
                            "cps_trx_id");
                }
            }

            if(transactionEntity.getCpsTransactionEntity() !=null) {
                var cpsTransactionEntity =transactionEntity.getCpsTransactionEntity();
                cpsTransactionEntity.setId(UUID.randomUUID());
                cpsTransactionEntity.setCorrelationId(cpsTransactionEntity.getId().toString());
                merge(cpsTransactionEntity,
                        PostgresTableNames.CPS_TRANSACTION,
                        "trx_id");
            }
        }

        entityManager.flush();

        System.out.println("Writing DpsTransactionEntity");
        System.out.println(items.getItems());
    }
}