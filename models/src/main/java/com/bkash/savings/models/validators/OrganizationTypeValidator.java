package com.bkash.savings.models.validators;


import com.bkash.savings.models.postgres.organization.OrganizationType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OrganizationTypeValidator implements ConstraintValidator<ValidOrganizationType, String> {
    @Override
    public void initialize(ValidOrganizationType constraintAnnotation) {
    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && isValidOrganizationType(value);
    }

    private boolean isValidOrganizationType(String organizationType) {
        for (OrganizationType ot : OrganizationType.values()) {
            if (ot.name().equals(organizationType)) {
                return true;
            }
        }
        return false;
    }

}
