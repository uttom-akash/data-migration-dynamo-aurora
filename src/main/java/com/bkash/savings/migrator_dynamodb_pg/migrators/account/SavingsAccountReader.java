package com.bkash.savings.migrator_dynamodb_pg.migrators.account;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.bkash.savings.migrator_dynamodb_pg.configs.DynamoDBTableNames;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.account.SavingsAccountEntity;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.DynamoDbReader;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@StepScope
public class SavingsAccountReader extends DynamoDbReader<SavingsAccountEntity> {


    private final DynamoDBMapper dynamoDBMapper;

    public SavingsAccountReader(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB amazonDynamoDB) {
        super(amazonDynamoDB, DynamoDBTableNames.TABLE_ACCOUNT);
        this.dynamoDBMapper = dynamoDBMapper;
    }


    @Override
    protected SavingsAccountEntity marshallIntoObject(Map<String, AttributeValue> item) {
        return dynamoDBMapper.marshallIntoObject(SavingsAccountEntity.class, item);
    }
}
