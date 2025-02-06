package com.bkash.savings.models.dto.gql;

import com.bkash.savings.common.utils.DateTimeUtils;
import com.bkash.savings.models.exception.gql.CustomerValidationException;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

import static com.bkash.savings.models.common.ApiResponseStatus.*;

@Builder
@Slf4j
public record Customer(String applicantName, String fatherName, String motherName, String dob, String walletNo,
                       String gender, String nidNumber, String profession, Address address) implements Serializable {


    /**
     * Validates the customer's age based on their date of birth.
     *
     * @throws CustomerValidationException if:
     *                                     - Date of birth is "N/A"
     *                                     - Date format is incorrect
     *                                     - Customer age is less than 18 years
     */
    public void validateAge() {
        if (StringUtils.isBlank(dob())) {
            throw new CustomerValidationException(CUSTOMER_AGE_VALIDATION_FAILED);
        }

        if (dob().equals("N/A")) {
            throw new CustomerValidationException(CUSTOMER_AGE_VALIDATION_FAILED);
        }
        int customerAge;
        try {
            customerAge = DateTimeUtils.getAgeFromDateOfBirth(dob());
        } catch (Exception exception) {
            throw new CustomerValidationException(DATE_FORMAT_IS_NOT_CORRECT);
        }
        if (customerAge < 18) {
            throw new CustomerValidationException(CUSTOMER_AGE_VALIDATION_FAILED);
        }
    }

    /**
     * Validates the customer's NID (National ID) against a provided nominee NID.
     *
     * @param nomineeNid The NID number of the nominee to validate against the customer's NID
     * @throws CustomerValidationException if the customer's NID is "N/A" or if it doesn't match the nominee's NID
     * @throws CustomerValidationException with CUSTOMER_KYC_VALIDATION_FAILED if customer NID is "N/A"
     * @throws CustomerValidationException with NOMINEE_VALIDATION_FAILED if customer NID doesn't match nominee NID
     */
    public void validateNid(String nomineeNid) {
        if (nidNumber().equals("N/A") || StringUtils.isBlank(nidNumber())) {
            log.info("Customer|validateNid:: Customer NID is N/A or empty: {}", nidNumber());
            throw new CustomerValidationException(CUSTOMER_KYC_VALIDATION_FAILED);
        }
        if (nidNumber().equalsIgnoreCase(nomineeNid)) {
            log.info("Customer|validateNid:: Customer NID: {} matches Nominee NID: {}", nidNumber(), nomineeNid);
            throw new CustomerValidationException(NOMINEE_VALIDATION_FAILED);
        }
    }
}