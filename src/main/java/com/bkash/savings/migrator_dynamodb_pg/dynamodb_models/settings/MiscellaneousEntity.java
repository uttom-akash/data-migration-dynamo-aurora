package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.settings;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.settings.entity.Value;

import java.util.List;

@DynamoDBTable(tableName = "miscellaneous")
public class MiscellaneousEntity {

    @DynamoDBHashKey
    private String key;

    private List<Value> values;

    @DynamoDBAttribute
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @DynamoDBAttribute
    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }
}


