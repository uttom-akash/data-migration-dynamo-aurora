package org.migration.transformers;

import java.math.BigDecimal;
import java.util.UUID;

public class TypeTransformer {

    private static final BigDecimal DEFAULT_BIG_DECIMAL = BigDecimal.valueOf(0.00);
    private static final Double DEFAULT_DOUBLE = 0.00;
    private static final UUID DEFAULT_UUID = UUID.fromString("87f29b1a-9c0b-467f-8b90-3f9db702ecdf");

    public static BigDecimal toBigDecimal(String value) {
        if (value == null) return null;

        return new BigDecimal(value);
    }

    public static BigDecimal toBigDecimalOrDefault(String value) {
        if (value == null) return DEFAULT_BIG_DECIMAL;

        return new BigDecimal(value);
    }

    public static Double toDouble(String value) {
        if (value == null) return null;

        return Double.parseDouble(value);
    }

    public static Double toDoubleOrDefault(String value) {
        if (value == null) return DEFAULT_DOUBLE;

        return Double.parseDouble(value);
    }


    public static UUID toUUIDO(String value) {
        if (value == null) return null;

        return UUID.fromString(value);
    }

    public static UUID toUUIDOrDefault(String value) {
        if (value == null) return DEFAULT_UUID;

        return UUID.fromString(value);
    }

    public static UUID toUUIDOrDefault(UUID value) {
        if (value == null) return DEFAULT_UUID;

        return value;
    }

    public static String getOrDefaultUuid(String value) {
        if (value == null) return DEFAULT_UUID.toString();

        return value;
    }
}
