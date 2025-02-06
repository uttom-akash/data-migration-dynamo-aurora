package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.nominee;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@DynamoDBDocument
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "nominees")
public class NomineesEntity {

    @DynamoDBHashKey
    private String walletId;

    private String walletNumber;
    private List<NomineeEntity> nominees;
}
