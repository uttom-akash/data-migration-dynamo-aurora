package com.bkash.savings.migrator_dynamodb_pg.migrators.product_type;

import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.settings.SettingsEntity;
import com.bkash.savings.models.postgres.product.ProductType;
import com.bkash.savings.models.postgres.settings.SettingsValue;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProductTypeProcessor implements ItemProcessor<SettingsEntity, ProductType> {

    private Iterator<ProductType> currentProductIterator;

    @Override
    public ProductType process(SettingsEntity settingsEntity) throws Exception {
        if (!settingsEntity.getKey().equals("product-types")) {
            return null;
        }

        if (currentProductIterator == null) {
            var productTypeValues = settingsEntity.getValues();
            currentProductIterator = (Iterator<ProductType>) productTypeValues
                    .stream()
                    .map(x ->
                            ProductType.builder()
//                            .id(UUID.fromString(x.getKey()))
                            .type(x.getText())
                            .build())
                    .toList()
                    .iterator();  // Create an iterator to return one at a time
        }

        return currentProductIterator.hasNext() ? currentProductIterator.next() : null;
    }
}






