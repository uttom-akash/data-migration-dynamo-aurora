package org.migration.migrators.transaction;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.dynamo.models.paymentlog.PaymentLog;
import org.migration.configs.DynamoDBTableNames;
import org.migration.migrators.base.DynamoDbReader;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@StepScope
public class PaymentLogReader extends DynamoDbReader<PaymentLog> {


    private final DynamoDBMapper dynamoDBMapper;

    public PaymentLogReader(AmazonDynamoDB dynamoDbClient, DynamoDBMapper dynamoDBMapper) {
        super(dynamoDbClient, DynamoDBTableNames.TABLE_PAYMENT_HISTORY);
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    protected PaymentLog marshallIntoObject(Map<String, AttributeValue> item) {
        return dynamoDBMapper.marshallIntoObject(PaymentLog.class, item);
    }

}