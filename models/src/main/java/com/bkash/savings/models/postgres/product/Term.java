package com.bkash.savings.models.postgres.product;

import com.bkash.savings.models.common.ApiResponseStatus;
import com.bkash.savings.models.exception.SavingsException;
import lombok.Getter;

@Getter
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

    public String description() {
        return description;
    }

    public String value() {
        return value;
    }

    public int rppEndDateExtendedDays() {
        return rppEndDateExtendedDays;
    }

    public static Term getByDbValue(String dbValue) {
        for (Term t : Term.values()) if (t.name().equals(dbValue)) return t;
        throw new SavingsException(ApiResponseStatus.INTERNAL_ERROR.code(), dbValue + " is not a valid term");
    }
}
