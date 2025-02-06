package org.migration.in_memory_data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.Getter;
import org.aurora.postgres.deposit_nominee.DepositNominee;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class InMemoryDepositNomineeManagement {

    private List<DepositNominee> nomineeEntities;

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeStep
    public void beforeStep() {
        nomineeEntities = new ArrayList<>();

        String jpql = "SELECT a FROM NomineeEntity a"; // JPQL query, adjust if needed

        Query query = entityManager.createQuery(jpql);
        var results = query.getResultList();
        nomineeEntities.addAll(results);

        System.out.println("Executed before step, fetched " + results.size() + " records.");
    }

    @AfterStep
    public void afterStep() {
//        nomineeEntities = null;
    }
}
