package org.migration.migrators.deposit_nominee;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.aurora.postgres.deposit_nominee.DepositNominee;
import org.migration.configs.PostgresTableNames;
import org.migration.migrators.base.PostgresWriter;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DepositNomineeWriter extends PostgresWriter implements ItemWriter<DepositNominee> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void write(Chunk<? extends DepositNominee> items) throws Exception {

        for (DepositNominee nomineeEntity : items.getItems()) {
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