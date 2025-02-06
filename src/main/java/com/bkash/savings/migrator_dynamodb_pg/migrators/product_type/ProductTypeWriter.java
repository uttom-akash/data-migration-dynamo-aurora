package com.bkash.savings.migrator_dynamodb_pg.migrators.product_type;

import com.bkash.savings.migrator_dynamodb_pg.migrators.common.PostgresWriter;
import com.bkash.savings.models.postgres.product.ProductType;
import com.bkash.savings.models.postgres.settings.SettingsEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeWriter extends PostgresWriter implements ItemWriter<ProductType> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void write(Chunk<? extends ProductType> items) throws Exception {

        for (ProductType productType : items.getItems()) {
            if(productType == null) continue;


            ProductType existingEntity = (ProductType) entityManager.createQuery(
                            "SELECT e FROM ProductType e WHERE e.type = :type")
                    .setParameter("type", productType.getType())
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            merge(productType, existingEntity);

//            merge(productType, "product_types", "type");
        }

        entityManager.flush();

        System.out.println("Writing DpsTransactionEntity");
        System.out.println(items.getItems());
    }
}