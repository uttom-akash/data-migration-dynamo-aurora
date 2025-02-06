package com.bkash.savings.migrator_dynamodb_pg.migrators.assisted;

import com.bkash.savings.migrator_dynamodb_pg.migrators.common.PostgresWriter;
import com.bkash.savings.models.postgres.account.AssistedSavingsEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class AssistedSavingsWriter extends PostgresWriter implements ItemWriter<AssistedSavingsEntity> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void write(Chunk<? extends AssistedSavingsEntity> items) throws Exception {

        for (AssistedSavingsEntity assistedSavingsEntity : items.getItems()) {
            AssistedSavingsEntity existingEntity = (AssistedSavingsEntity) entityManager.createQuery(
                            "SELECT e FROM AssistedSavingsEntity e WHERE e.requestId = :requestId")
                    .setParameter("requestId", assistedSavingsEntity.getRequestId())
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            merge(assistedSavingsEntity, existingEntity);
        }

        entityManager.flush();

        System.out.println("Writing DpsTransactionEntity");
        System.out.println(items.getItems());
    }
}