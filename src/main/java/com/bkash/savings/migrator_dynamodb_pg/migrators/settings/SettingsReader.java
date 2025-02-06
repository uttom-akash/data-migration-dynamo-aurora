package com.bkash.savings.migrator_dynamodb_pg.migrators.settings;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.bkash.savings.migrator_dynamodb_pg.configs.DynamoDBTableNames;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.settings.SettingsEntity;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.DynamoDbReader;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@StepScope
public class SettingsReader extends DynamoDbReader<SettingsEntity> {


    private final DynamoDBMapper dynamoDBMapper;

    public SettingsReader(AmazonDynamoDB amazonDynamoDB, DynamoDBMapper dynamoDBMapper) {
        super(amazonDynamoDB, DynamoDBTableNames.TABLE_SETTINGS);
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    protected SettingsEntity marshallIntoObject(Map<String, AttributeValue> item) {
        return dynamoDBMapper.marshallIntoObject(SettingsEntity.class, item);
    }
}
