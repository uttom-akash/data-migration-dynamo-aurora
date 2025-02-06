package com.bkash.savings.migrator_dynamodb_pg.in_memory_data;

import com.bkash.savings.models.postgres.product.ProductEntity;
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
public class InMemoryProductManagement {

    private List<ProductEntity> productEntities;

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeStep
    public void beforeStep() {
        productEntities = new ArrayList<>();

        String jpql = "SELECT a FROM ProductEntity a"; // JPQL query, adjust if needed

        Query query = entityManager.createQuery(jpql);
        var results = query.getResultList();
        productEntities.addAll(results);

        System.out.println("Executed before step, fetched " + results.size() + " records.");
    }

    @AfterStep
    public void afterStep() {
//        nomineeEntities = null;
    }
}
