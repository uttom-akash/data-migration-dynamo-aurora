package org.migration.migrators.product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.aurora.postgres.product.Product;
import org.migration.configs.PostgresTableNames;
import org.migration.migrators.base.PostgresWriter;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class ProductWriter extends PostgresWriter implements ItemWriter<Product> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void write(Chunk<? extends Product> items) throws Exception {

        for (Product product : items.getItems()) {
            product.setId(UUID.randomUUID());
            merge(product, PostgresTableNames.PRODUCT, "product_id");
        }

        entityManager.flush();

        log.debug("Wrote {} products", items.getItems().size());
    }
}
