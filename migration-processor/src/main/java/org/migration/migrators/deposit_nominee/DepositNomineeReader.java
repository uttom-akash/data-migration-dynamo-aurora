package org.migration.migrators.deposit_nominee;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.dynamo.models.dps_nominee.Nominee;
import org.migration.configs.DynamoDBTableNames;
import org.migration.migrators.base.DynamoDbReader;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@StepScope
public class DepositNomineeReader extends DynamoDbReader<Nominee> {

    private final DynamoDBMapper dynamoDBMapper;

    public DepositNomineeReader(AmazonDynamoDB dynamoDbClient, DynamoDBMapper dynamoDBMapper) {
        super(dynamoDbClient, DynamoDBTableNames.TABLE_NOMINEE);
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    protected Nominee marshallIntoObject(Map<String, AttributeValue> item) {
        return dynamoDBMapper.marshallIntoObject(Nominee.class, item);
    }
}
