package com.bkash.savings.models.validators;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValueValidatorImpl implements ConstraintValidator<EnumValueValidator, CharSequence> {
    private List<String> acceptedValues;
    private boolean isNullOrEmptyAllowed;

    @Override
    public void initialize(EnumValueValidator annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
        isNullOrEmptyAllowed = annotation.isNullOrEmptyAllowed();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank((String) value)) {
            return isNullOrEmptyAllowed;
        }
        return acceptedValues.contains(value.toString());
    }
}
