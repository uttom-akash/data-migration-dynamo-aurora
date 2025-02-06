package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.settings.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@ToString
@AllArgsConstructor
@DynamoDBDocument
public class Value {

    private String key;

    private String text;

    public Value() {
        this.key = UUID.randomUUID().toString();
    }

    public Value(String text) {
        this.text = text;
    }

    @DynamoDBAttribute(attributeName = "key")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @DynamoDBAttribute(attributeName = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}