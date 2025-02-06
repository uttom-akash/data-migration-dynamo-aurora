package com.bkash.savings.migrator_dynamodb_pg.migrators.nominee;

import com.bkash.savings.migrator_dynamodb_pg.configs.PostgresTableNames;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.PostgresWriter;
import com.bkash.savings.models.postgres.nominee.NomineeEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class NomineeWriter extends PostgresWriter implements ItemWriter<NomineeEntity> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void write(Chunk<? extends NomineeEntity> items) throws Exception {

        for (NomineeEntity nomineeEntity : items.getItems()) {
            nomineeEntity.setId(UUID.randomUUID());
            merge(nomineeEntity,
                    PostgresTableNames.Nominee,
                    "wallet_id, nid_number");
        }

        entityManager.flush();

        System.out.println("Writing DpsTransactionEntity");
        System.out.println(items.getItems());
    }
}