package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.idgenerator;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@DynamoDBTable(tableName = "id-generator")
public class IdGeneratorEntity {
    @DynamoDBHashKey
    private String pk;
    @DynamoDBRangeKey
    private String sk;
    private Long value;
}
