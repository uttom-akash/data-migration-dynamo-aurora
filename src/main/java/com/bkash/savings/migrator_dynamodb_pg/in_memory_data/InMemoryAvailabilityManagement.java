package com.bkash.savings.migrator_dynamodb_pg.in_memory_data;

import com.bkash.savings.migrator_dynamodb_pg.migrators.common.PostgresWriter;
import com.bkash.savings.models.postgres.product.Availability;
import com.bkash.savings.models.postgres.product.ProductEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.batch.core.annotation.BeforeProcess;
import org.springframework.transaction.annotation.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Getter
@Setter
public class InMemoryAvailabilityManagement extends PostgresWriter {

    private List<Availability> availabilities;

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeProcess
    @Transactional
    public void beforeStep() {
        availabilities = new ArrayList<>();
        availabilities.add(Availability.builder().option("AAPP").build());
        availabilities.add(Availability.builder().option("CAPP").build());
        availabilities.add(Availability.builder().option("MAPP").build());

        for(var availability: availabilities) {

            Availability existingEntity = (Availability) entityManager.createQuery(
                            "SELECT e FROM Availability e WHERE e.option = :option")
                    .setParameter("option", availability.getOption())
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            merge(availability, existingEntity);
        }
        entityManager.flush();

        System.out.println("Executed before step, fetched " + availabilities.size() + " records.");
    }

    @AfterStep
    public void afterStep() {
//        nomineeEntities = null;
    }
}
