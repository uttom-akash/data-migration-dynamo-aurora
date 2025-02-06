package com.bkash.savings.migrator_dynamodb_pg.migrators.notification;

import com.bkash.savings.migrator_dynamodb_pg.configs.PostgresTableNames;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.PostgresWriter;
import com.bkash.savings.models.postgres.notification.NotificationEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class NotificationWriter extends PostgresWriter implements ItemWriter<NotificationEntity> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void write(Chunk<? extends NotificationEntity> items) throws Exception {

        for (NotificationEntity notification : items.getItems()) {
            notification.setId(UUID.randomUUID());
            merge(notification, PostgresTableNames.NOTIFICATION, "event");
        }

        entityManager.flush();

        System.out.println("Writing DpsTransactionEntity");
        System.out.println(items.getItems());
    }
}