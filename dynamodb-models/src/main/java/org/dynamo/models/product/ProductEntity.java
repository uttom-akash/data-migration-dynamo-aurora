package org.dynamo.models.product;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@DynamoDBTable(tableName = "product")
public class ProductEntity {

    @DynamoDBHashKey
    private String productId;

    private String name;

    private String category;

    private String price;

    private Integer stockQuantity;

    private String createdAt;
}
