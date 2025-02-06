package com.bkash.savings.migrator_dynamodb_pg.mappers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateConversion {

    public static ZonedDateTime toZonedDateTime(String date) {
        if (date == null) {
            return ZonedDateTime.now(ZoneOffset.UTC);  // Return default ZonedDateTime (current time in UTC)
        }

        // Use a formatter to parse the date, expecting a pattern like "yyyy-MM-dd HH:mm:ss:SSS Z"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS Z");

        try {
            return ZonedDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            // Log the exception if necessary or handle it in a way that fits your needs
            return ZonedDateTime.now(ZoneOffset.UTC);  // Return default value in case of parse failure
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

    public static BigDecimal toBigDecimal(String value) {
        if(value ==null) return null;

        return new BigDecimal(value);
    }

    public static Double toDouble(String value) {
        if(value ==null) return null;

        return Double.parseDouble(value);
    }

    public static String getOrEmptyString(String value){
        return  value == null ? " " : value;
    }

}
