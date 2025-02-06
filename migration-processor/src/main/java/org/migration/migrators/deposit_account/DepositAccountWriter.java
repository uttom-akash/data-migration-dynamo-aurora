package org.migration.migrators.deposit_account;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.aurora.postgres.deposit_account.DepositAccount;
import org.migration.migrators.base.PostgresWriter;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class DepositAccountWriter extends PostgresWriter implements ItemWriter<DepositAccount> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void write(Chunk<? extends DepositAccount> items) throws Exception {

        for (DepositAccount savingsAccountEntity : items.getItems()) {

            DepositAccount existingEntity = (DepositAccount) entityManager.createQuery(
                            "SELECT e FROM SavingsAccountEntity e" +
                                    " WHERE (e.walletId = :walletId and e.savingsId = :savingsId) ")
                    .setParameter("walletId", savingsAccountEntity.getWalletId())
                    .setParameter("savingsId", savingsAccountEntity.getSavingsId())
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

