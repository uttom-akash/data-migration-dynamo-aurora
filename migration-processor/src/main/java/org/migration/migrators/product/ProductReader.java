package org.migration.migrators.product;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.dynamo.models.product.ProductEntity;
import org.migration.configs.DynamoDBTableNames;
import org.migration.migrators.base.DynamoDbReader;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@StepScope
public class ProductReader extends DynamoDbReader<ProductEntity> {

    private final DynamoDBMapper dynamoDBMapper;

    public ProductReader(AmazonDynamoDB dynamoDbClient, DynamoDBMapper dynamoDBMapper) {
        super(dynamoDbClient, DynamoDBTableNames.TABLE_PRODUCT);
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    protected ProductEntity marshallIntoObject(Map<String, AttributeValue> item) {
        return dynamoDBMapper.marshallIntoObject(ProductEntity.class, item);
    }
}
