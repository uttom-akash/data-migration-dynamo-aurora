package org.migration.migrators.product;

import org.aurora.postgres.product.Product;
import org.dynamo.models.product.ProductEntity;
import org.migration.transformers.DateTransformer;
import org.migration.transformers.TypeTransformer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ProductProcessor implements ItemProcessor<ProductEntity, Product> {

    @Override
    public Product process(ProductEntity item) throws Exception {
        return Product.builder()
                .productId(item.getProductId())
                .name(item.getName())
                .category(item.getCategory())
                .price(TypeTransformer.toBigDecimal(item.getPrice()))
                .stockQuantity(item.getStockQuantity())
                .listedAt(DateTransformer.toZonedDateTime(item.getCreatedAt()))
                .build();
    }
}
