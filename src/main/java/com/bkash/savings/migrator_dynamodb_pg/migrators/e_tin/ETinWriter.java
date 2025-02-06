package com.bkash.savings.migrator_dynamodb_pg.migrators.e_tin;

import com.bkash.savings.migrator_dynamodb_pg.configs.PostgresTableNames;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.PostgresWriter;
import com.bkash.savings.models.postgres.etin.ETINEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ETinWriter extends PostgresWriter implements ItemWriter<ETINEntity> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void write(Chunk<? extends ETINEntity> items) throws Exception {

        for (ETINEntity etinEntity : items.getItems()) {
            etinEntity.setId(UUID.randomUUID());
            merge(etinEntity, PostgresTableNames.E_TIN, "wallet_id");
        }

        entityManager.flush();

        System.out.println("Writing DpsTransactionEntity");
        System.out.println(items.getItems());
    }
}