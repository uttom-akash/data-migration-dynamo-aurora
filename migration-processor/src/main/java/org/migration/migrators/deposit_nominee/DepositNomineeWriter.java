package org.migration.migrators.deposit_nominee;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.aurora.postgres.deposit_nominee.DepositNominee;
import org.migration.configs.PostgresTableNames;
import org.migration.migrators.base.PostgresWriter;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
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

        log.debug("Writing DepositNominee");
        log.debug("" + items.getItems().size());
    }
}