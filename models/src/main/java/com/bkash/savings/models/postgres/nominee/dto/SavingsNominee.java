package com.bkash.savings.models.postgres.nominee.dto;

import com.bkash.savings.models.postgres.nominee.NomineeEntity;
import com.bkash.savings.models.postgres.nominee.Relation;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

// Score to improve
/**
 * this class is more or less the same as {@link NomineeDto}. Might remove this in the future.
 */
@Deprecated
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SavingsNominee implements Serializable {

    // nid number
    @NotBlank(message = "NID number of the nominee can not be empty")
    private String nidNumber;

    // date of birth
    @NotBlank(message = "Birth date of the nominee can not be empty")
    private String dob;

    // relation [sister/brother/spouse]
    @NotBlank(message = "Nominee's relation with the account holder is required")
    private String relation;

    public NomineeEntity toNominee() {
        NomineeEntity nominee = new NomineeEntity();
        nominee.setRelation(Relation.valueOf(this.relation));
        nominee.setDob(LocalDate.parse(this.dob));
        nominee.setNidNumber(this.nidNumber);
        return nominee;
    }
}
