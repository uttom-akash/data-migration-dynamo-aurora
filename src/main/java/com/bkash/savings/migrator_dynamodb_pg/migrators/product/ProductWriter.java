package com.bkash.savings.migrator_dynamodb_pg.migrators.product;

import com.bkash.savings.migrator_dynamodb_pg.configs.PostgresTableNames;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.PostgresWriter;
import com.bkash.savings.models.postgres.product.ProductEntity;
import com.bkash.savings.models.postgres.settings.SettingsEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.UUID;

@Component
public class ProductWriter extends PostgresWriter implements ItemWriter<ProductEntity> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void write(Chunk<? extends ProductEntity> items) throws Exception {

        HashMap<String, Integer> dict = new HashMap<String, Integer>();

        for (ProductEntity product : items.getItems()) {
              product.setId(UUID.randomUUID());
              dict.put(product.getProductId(), dict.getOrDefault(product.getProductId(), 0) +1 );

//              merge(product, "product", "product_id", true);

//            Todo-akash : optimize the code
              ProductEntity existingEntity = (ProductEntity) entityManager.createQuery(
                            "SELECT e FROM ProductEntity e WHERE e.productId = :product_id")
                    .setParameter("product_id", product.getProductId())
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

              if(existingEntity ==null){
                  entityManager.persist(product);
              }
              else{
                  product.setId(existingEntity.getId());
              }

              for(var productAvailability : product.getAvailabilityOptions()){

                String sql = "INSERT INTO product_availability (availability_id, product_id) " +
                        "VALUES (:availabilityId, :productId) " +
                        "ON CONFLICT (availability_id, product_id) DO NOTHING";

                Query query = entityManager.createNativeQuery(sql);
                query.setParameter("availabilityId", productAvailability.getId());
                query.setParameter("productId", existingEntity.getId());
                query.executeUpdate();
             }
            entityManager.flush();
        }
        System.out.println("Writing DpsTransactionEntity");
        System.out.println(items.getItems());
    }
}