package com.bkash.savings.migrator_dynamodb_pg.migrators.transaction;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.bkash.savings.migrator_dynamodb_pg.configs.DynamoDBTableNames;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.paymenthistory.PaymentEntity;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.DynamoDbReader;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@StepScope
public class PaymentHistoryReader extends DynamoDbReader<PaymentEntity> {


    private final DynamoDBMapper dynamoDBMapper;

    public PaymentHistoryReader(AmazonDynamoDB dynamoDbClient, DynamoDBMapper dynamoDBMapper) {
        super(dynamoDbClient, DynamoDBTableNames.TABLE_PAYMENT_HISTORY);
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    protected PaymentEntity marshallIntoObject(Map<String, AttributeValue> item) {
        return dynamoDBMapper.marshallIntoObject(PaymentEntity.class, item);
    }

}