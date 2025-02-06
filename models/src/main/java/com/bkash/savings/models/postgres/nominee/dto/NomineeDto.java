package com.bkash.savings.models.postgres.nominee.dto;

import com.bkash.savings.common.utils.DateTimeUtils;
import com.bkash.savings.models.exception.gql.CustomerValidationException;
import com.bkash.savings.models.postgres.nominee.Relation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

import static com.bkash.savings.models.common.ApiResponseStatus.DATE_FORMAT_IS_NOT_CORRECT;
import static com.bkash.savings.models.common.ApiResponseStatus.NOMINEE_AGE_VALIDATION_FAILED;

/**
 * DTO for {@link com.bkash.savings.models.postgres.nominee.NomineeEntity}
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class NomineeDto implements Serializable {
    private String id;
    private String nidNumber;
    private String dob;
    private Relation relation;
    @JsonIgnore
    private String walletId;
    @JsonIgnore
    private Integer version;
    private String lastUsedTime;

    public void validateNomineeAge() {
        if (StringUtils.isEmpty(dob)) {
            throw new CustomerValidationException(DATE_FORMAT_IS_NOT_CORRECT);
        }
        int customerAge;
        try {
            customerAge = DateTimeUtils.getAgeFromDateOfBirth(dob);
        } catch (Exception exception) {
            throw new CustomerValidationException(DATE_FORMAT_IS_NOT_CORRECT);
        }
        if (customerAge < 18) {
            throw new CustomerValidationException(NOMINEE_AGE_VALIDATION_FAILED);
        }
    }
}