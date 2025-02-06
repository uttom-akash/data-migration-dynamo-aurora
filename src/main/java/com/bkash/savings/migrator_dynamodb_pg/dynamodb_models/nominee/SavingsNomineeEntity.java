package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.nominee;

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
public class SavingsNomineeEntity implements Serializable {

    private String id;

    // nid number
    private String nidNumber;

    // date of birth
    private String dob;

    // relation [sister/brother/spouse]
    private String relation;

//    public SavingsNominee toNominee() {
//        SavingsNominee nominee = new SavingsNominee();
//        nominee.setRelation(this.relation);
//        nominee.setDob(this.dob);
//        nominee.setNidNumber(this.nidNumber);
//        return nominee;
//    }
}
