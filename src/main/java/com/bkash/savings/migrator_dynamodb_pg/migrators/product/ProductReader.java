package com.bkash.savings.migrator_dynamodb_pg.migrators.product;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.bkash.savings.migrator_dynamodb_pg.configs.DynamoDBTableNames;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.product.ProductSchemeEntity;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.DynamoDbReader;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@StepScope
public class ProductReader extends DynamoDbReader<ProductSchemeEntity> {

    private final DynamoDBMapper dynamoDBMapper;

    public ProductReader(AmazonDynamoDB dynamoDbClient, DynamoDBMapper dynamoDBMapper) {
        super(dynamoDbClient, DynamoDBTableNames.TABLE_PRODUCT);
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    protected ProductSchemeEntity marshallIntoObject(Map<String, AttributeValue> item) {
        return dynamoDBMapper.marshallIntoObject(ProductSchemeEntity.class, item);
    }
}
