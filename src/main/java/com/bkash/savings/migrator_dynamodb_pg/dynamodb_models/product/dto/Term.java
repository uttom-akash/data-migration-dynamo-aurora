package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.product.dto;

import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.common.util.ApiResponseStatus;
import com.bkash.savings.models.exception.SavingsException;

//@ApiModel
public enum Term {
    MONTHLY("M", "Monthly", 7),
    BIWEEKLY("BW", "Biweekly", 4),
    WEEKLY("W", "Weekly", 4);

    private final String description;
    private final String value;

    private final int rppEndDateExtendedDays;

    Term(String description, String value, int rppEndDateExtendedDays) {
        this.description = description;
        this.value = value;
        this.rppEndDateExtendedDays = rppEndDateExtendedDays;
    }

    public static Term getByDbValue(String dbValue) {
        for (Term t : Term.values()) if (t.name().equals(dbValue)) return t;
        throw new SavingsException(ApiResponseStatus.INTERNAL_ERROR.code(), dbValue + " is not a valid term");
    }

    public String description() {
        return description;
    }

    public String value() {
        return value;
    }

    public int rppEndDateExtendedDays() {
        return rppEndDateExtendedDays;
    }
}
