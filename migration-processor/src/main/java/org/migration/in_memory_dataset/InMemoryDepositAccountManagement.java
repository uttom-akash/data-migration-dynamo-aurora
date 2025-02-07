package org.migration.in_memory_dataset;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.Getter;
import org.aurora.postgres.deposit_account.DepositAccount;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
public class InMemoryDepositAccountManagement {

    private List<DepositAccount> savingsAccountEntities;
    @PersistenceContext
    private EntityManager entityManager;

    @BeforeStep
    public void beforeStep() {

        String jpql = "SELECT a FROM SavingsAccountEntity a";
        Query query = entityManager.createQuery(jpql);
        savingsAccountEntities = query.getResultList();

        // Any setup or validation logic before the step runs
        System.out.println("Executing logic before the step");
    }

    @AfterStep
    public void afterStep() {

    }
}
