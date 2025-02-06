package com.bkash.savings.migrator_dynamodb_pg.migrators.account;

import com.bkash.savings.migrator_dynamodb_pg.migrators.common.PostgresWriter;
import com.bkash.savings.models.postgres.account.AssistedSavingsEntity;
import com.bkash.savings.models.postgres.account.SavingsAccountEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SavingsAccountWriter extends PostgresWriter implements ItemWriter<SavingsAccountEntity> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void write(Chunk<? extends SavingsAccountEntity> items) throws Exception {

        for (SavingsAccountEntity savingsAccountEntity : items.getItems()) {

            SavingsAccountEntity existingEntity = (SavingsAccountEntity) entityManager.createQuery(
                            "SELECT e FROM SavingsAccountEntity e" +
                                    " WHERE (e.walletId = :walletId and e.savingsId = :savingsId) " +
                                    "or (e.walletId = :walletId " +
                                    "and e.fiAccountId = :fiAccountId and e.fiAccountNo = :fiAccountNo)")
                    .setParameter("walletId", savingsAccountEntity.getWalletId())
                    .setParameter("savingsId", savingsAccountEntity.getSavingsId())
                    .setParameter("fiAccountId", savingsAccountEntity.getFiAccountId())
                    .setParameter("fiAccountNo", savingsAccountEntity.getFiAccountNo())
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            merge(savingsAccountEntity, existingEntity);
        }

        entityManager.flush();

        System.out.println("Writing DpsTransactionEntity");
        System.out.println(items.getItems());
    }
}

