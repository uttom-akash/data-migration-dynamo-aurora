package org.migration.transformers;

import java.math.BigDecimal;

public class TypeTransformer {

    public static BigDecimal toBigDecimal(String value) {
        if (value == null) return null;

        return new BigDecimal(value);
    }
}
