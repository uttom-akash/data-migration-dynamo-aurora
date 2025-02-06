package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.product;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@DynamoDBDocument
@ToString
@Getter
@Setter
public class ProductEntity implements Serializable {

    @DynamoDBAttribute
    private String tenure;

    @DynamoDBAttribute
    private String term;

    @DynamoDBAttribute
    private String amount;

    @DynamoDBAttribute
    private String productId;

    @DynamoDBAttribute
    private String autoRenewal;

    @DynamoDBAttribute
    private String autoRenewalMode;
}
