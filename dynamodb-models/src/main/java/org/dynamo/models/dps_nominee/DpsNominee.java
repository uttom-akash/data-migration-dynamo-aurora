package org.dynamo.models.dps_nominee;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@DynamoDBDocument
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DpsNominee implements Serializable {

    private String id;

    private String nidNumber;

    private String dob;

    private String relation;
}
