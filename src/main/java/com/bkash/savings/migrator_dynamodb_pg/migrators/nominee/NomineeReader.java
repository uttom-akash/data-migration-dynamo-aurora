package com.bkash.savings.migrator_dynamodb_pg.migrators.nominee;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.bkash.savings.migrator_dynamodb_pg.configs.DynamoDBTableNames;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.nominee.NomineeEntity;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.DynamoDbReader;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@StepScope
public class NomineeReader extends DynamoDbReader<NomineeEntity> {

    private final DynamoDBMapper dynamoDBMapper;

    public NomineeReader(AmazonDynamoDB dynamoDbClient, DynamoDBMapper dynamoDBMapper) {
        super(dynamoDbClient, DynamoDBTableNames.TABLE_NOMINEE);
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    protected NomineeEntity marshallIntoObject(Map<String, AttributeValue> item) {
        return dynamoDBMapper.marshallIntoObject(NomineeEntity.class, item);
    }
}
