package com.bkash.savings.migrator_dynamodb_pg.migrators.assisted;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.bkash.savings.migrator_dynamodb_pg.configs.DynamoDBTableNames;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.assisted.AssistedSavingsEntity;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.DynamoDbReader;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@StepScope
public class AssistedSavingsReader extends DynamoDbReader<AssistedSavingsEntity> {

    private final DynamoDBMapper dynamoDBMapper;

    public AssistedSavingsReader(AmazonDynamoDB dynamoDbClient, DynamoDBMapper dynamoDBMapper) {
        super(dynamoDbClient, DynamoDBTableNames.TABLE_ASSISTED_SAVINGS);
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    protected AssistedSavingsEntity marshallIntoObject(Map<String, AttributeValue> item) {
        return dynamoDBMapper.marshallIntoObject(AssistedSavingsEntity.class, item);
    }
}
