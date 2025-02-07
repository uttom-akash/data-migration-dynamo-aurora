package org.migration.migrators.deposit_account;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.aurora.postgres.deposit_account.DepositAccount;
import org.migration.migrators.base.PostgresWriter;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DepositAccountWriter extends PostgresWriter implements ItemWriter<DepositAccount> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void write(Chunk<? extends DepositAccount> items) throws Exception {

        for (DepositAccount depositAccount : items.getItems()) {

            DepositAccount existingEntity = (DepositAccount) entityManager.createQuery(
                            "SELECT e FROM DepositAccount e" +
                                    " WHERE (e.walletId = :walletId and e.savingsId = :savingsId) ")
                    .setParameter("walletId", depositAccount.getWalletId())
                    .setParameter("savingsId", depositAccount.getSavingsId())
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            merge(depositAccount, existingEntity);
        }

        entityManager.flush();

        log.debug("Writing DpsTransactionEntity");
        System.out.println(items.getItems());
    }
}

