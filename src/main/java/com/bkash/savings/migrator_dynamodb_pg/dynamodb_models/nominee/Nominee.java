package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.nominee;

import com.bkash.savings.models.postgres.nominee.Relation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Nominee implements Serializable {
    private String id;

    private String walletNumber;

    private String walletId;

    private String nidNumber;

    private String dob;

    private Relation relation;

    private String addedTime;

    private String lastUpdatedTime;

    private String lastUsedTime;

    @JsonIgnore
    public NomineeEntity toEntity() {
        NomineeEntity nominee = new NomineeEntity();
        nominee.setNidNumber(nidNumber);
        nominee.setDob(dob);
        nominee.setRelation(relation.toString());

        return nominee;
    }
}
