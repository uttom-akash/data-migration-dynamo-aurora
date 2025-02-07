package org.migration.transformers;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public class DateTransformer {

    private static final ZonedDateTime DEFAULT_ZONED_DATE_TIME = ZonedDateTime.parse("1988-11-26 12:00:00:000 +0000");
    private static final LocalDate DEFAULT_LOCAL_DATE = LocalDate.parse("1988-11-26");

    public static ZonedDateTime toZonedDateTime(String date) {
        if (date == null) return null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS Z");

        try {
            return ZonedDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static ZonedDateTime toZonedDateTimeOrDefault(String date) {
        if (date == null) return DEFAULT_ZONED_DATE_TIME;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS Z");

        try {
            return ZonedDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            return DEFAULT_ZONED_DATE_TIME;
        }
    }

    // Method to convert a string to LocalDate with a default return value
    public static LocalDate toLocalDate(String date) {
        if (date == null) {
            return LocalDate.now();  // Return default LocalDate (current date)
        }

        try {
            return LocalDate.parse(date);  // Default parsing assuming ISO-8601 format (yyyy-MM-dd)
        } catch (DateTimeParseException e) {
            return LocalDate.now();  // Return default value in case of parse failure
        }
    }
}
