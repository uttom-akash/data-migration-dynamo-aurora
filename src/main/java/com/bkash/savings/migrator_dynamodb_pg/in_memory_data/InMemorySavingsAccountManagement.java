package com.bkash.savings.migrator_dynamodb_pg.in_memory_data;

import com.bkash.savings.models.postgres.account.SavingsAccountEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.Getter;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class InMemorySavingsAccountManagement {

    private List<SavingsAccountEntity> savingsAccountEntities;
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
