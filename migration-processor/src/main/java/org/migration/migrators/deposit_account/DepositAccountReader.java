package org.migration.migrators.deposit_account;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.dynamo.models.dps_account.DpsAccountEntity;
import org.migration.configs.DynamoDBTableNames;
import org.migration.migrators.base.DynamoDbReader;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@StepScope
public class DepositAccountReader extends DynamoDbReader<DpsAccountEntity> {

    private final DynamoDBMapper dynamoDBMapper;

    public DepositAccountReader(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB amazonDynamoDB) {
        super(amazonDynamoDB, DynamoDBTableNames.TABLE_ACCOUNT);
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    protected DpsAccountEntity marshallIntoObject(Map<String, AttributeValue> item) {
        return dynamoDBMapper.marshallIntoObject(DpsAccountEntity.class, item);
    }
}
