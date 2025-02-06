package com.bkash.savings.models.validators;



import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OrganizationTypeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidOrganizationType {

    String message() default "Invalid organization type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
