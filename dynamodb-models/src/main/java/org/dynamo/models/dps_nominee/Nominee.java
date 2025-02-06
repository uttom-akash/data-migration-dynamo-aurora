package org.dynamo.models.dps_nominee;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@DynamoDBDocument
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "nominee")
@Builder
@Accessors(chain = true)
public class Nominee implements Serializable {
    // unique id
    @DynamoDBRangeKey
    private String id;

    // Wallet id [primary key]
    @DynamoDBHashKey
    private String walletId;

    // Wallet number
    private String walletNumber;

    // nid number
    private String nidNumber;

    // date of birth
    private String dob;

    // relation [sister/brother/spouse]
    private String relation;

    // Nominee add time
    private String addedTime;

    // Nominee update time
    private String lastUpdatedTime;

    // last used time
    private String lastUsedTime;

}
