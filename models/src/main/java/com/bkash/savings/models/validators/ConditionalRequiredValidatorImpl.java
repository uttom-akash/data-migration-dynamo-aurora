package com.bkash.savings.models.validators;

import org.apache.commons.lang3.StringUtils;

import com.bkash.savings.models.dto.product.ProductSchemesFilterRequest;
import com.bkash.savings.models.dto.product.ProductSchemesFilterRequest.FilterType;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConditionalRequiredValidatorImpl implements ConstraintValidator<ConditionalRequiredFieldValidator, Object> {


    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        if(value instanceof ProductSchemesFilterRequest) {
            return validateProductSchemesFilterRequest((ProductSchemesFilterRequest) value, context);
        }
        else {
            log.warn("WARN: No Conditional validation is written for {}", value.getClass().getSimpleName());
            return true;
        }
    }

    private boolean validateProductSchemesFilterRequest(ProductSchemesFilterRequest productSchemesFilterRequest, ConstraintValidatorContext context) {
        boolean isValid = true;
        if(productSchemesFilterRequest.getFilterType() == FilterType.GOAL_BASED) {
            if(StringUtils.isBlank(productSchemesFilterRequest.getTargetAmount())) {
                isValid = false;
                context.buildConstraintViolationWithTemplate("Target amount is required for GOAL_BASED").addConstraintViolation();
            }
        }
        else if(productSchemesFilterRequest.getFilterType() == FilterType.INSTALMENT_AMOUNT_BASED) {
            if(StringUtils.isBlank(productSchemesFilterRequest.getInstalmentAmount())) {
                isValid = false;
                context.buildConstraintViolationWithTemplate("Instalment amount is required for INSTALMENT_AMOUNT_BASED").addConstraintViolation();
            }
        }
        return isValid;
    }
}
