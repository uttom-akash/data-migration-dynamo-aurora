package com.bkash.savings.migrator_dynamodb_pg.migrators.e_tin;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.bkash.savings.migrator_dynamodb_pg.configs.DynamoDBTableNames;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.etin.EtinEntity;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.DynamoDbReader;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@StepScope
public class ETinReader extends DynamoDbReader<EtinEntity> {

    private final DynamoDBMapper dynamoDBMapper;

    public ETinReader(AmazonDynamoDB dynamoDbClient, DynamoDBMapper dynamoDBMapper) {
        super(dynamoDbClient, DynamoDBTableNames.TABLE_CUSTOMER_ETIN);
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    protected EtinEntity marshallIntoObject(Map<String, AttributeValue> item) {
        return dynamoDBMapper.marshallIntoObject(EtinEntity.class, item);
    }
}
