package org.migration.transformers;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTransformer {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS Z");

    public static ZonedDateTime toZonedDateTime(String date) {
        if (date == null) return null;

        try {
            return ZonedDateTime.parse(date, FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
