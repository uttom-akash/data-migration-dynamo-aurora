package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.common.util;

//import com.bkash.savings.models.account.SavingsAccountEntity;

import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.account.SavingsAccountEntity;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.product.dto.Term;
import com.bkash.savings.models.exception.SavingsException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
public class DateTimeUtils {

    public static final String ASIA_DHAKA_ZONE_ID = "Asia/Dhaka";
    public static final String DATE_PATTERN_NOTIFICATION = "dd/MM/yyyy";
    public static final String FI_OR_CIMT_DATE_FORMAT = "yyyyMMddHHmmss";
    public static final String SHORT_DATE_FORMAT = "yyyyMMdd";
    public static final String CLASSIC_SHORT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DYNAMO_DB_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS Z";
    public static final String RPP_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String GQL_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss:SSS";
    public static final String SCHEDULER_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String RPP_CANCELLED_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final ZoneId ZONE_ID_DHAKA = ZoneId.of(ASIA_DHAKA_ZONE_ID);
    public static final ZoneId ZONE_ID_UTC = ZoneId.of("UTC");
    public static final SimpleDateFormat RPP_HEADER_TIME_STAMP = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    public static final SimpleDateFormat DYNAMO_DB_DATE_FORMATTER_FOR_PRODUCT_FILTER = getDynamoDBDateFormatter("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    public static final DateTimeFormatter FI_OR_CIMT_DATE_FORMATTER = DateTimeFormatter.ofPattern(FI_OR_CIMT_DATE_FORMAT);
    public static final DateTimeFormatter SCHEDULER_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(SCHEDULER_DATE_TIME_FORMAT);
    public static final DateTimeFormatter LOCAL_DATE_DYNAMO_DB_FORMATTER = DateTimeFormatter.ofPattern(DYNAMO_DB_DATE_FORMAT);
    public static final DateTimeFormatter SHORT_DATE_FORMATTER = DateTimeFormatter.ofPattern(SHORT_DATE_FORMAT);
    public static final DateTimeFormatter CLASSIC_SHORT_DATE_FORMATTER = DateTimeFormatter.ofPattern(CLASSIC_SHORT_DATE_FORMAT);
    public static final DateTimeFormatter LOCAL_DATE_FI_FORMATTER = DateTimeFormatter.ofPattern(FI_OR_CIMT_DATE_FORMAT);
    public static final DateTimeFormatter RPP_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(RPP_DATE_TIME_FORMAT);
    public static final DateTimeFormatter GQL_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(GQL_DATE_TIME_FORMAT);
    public static final DateTimeFormatter DYNAMODB_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DYNAMO_DB_DATE_FORMAT);
    public static final DateTimeFormatter FORMATTED_DATE_FOR_NOTIFICATION = DateTimeFormatter.ofPattern(DATE_PATTERN_NOTIFICATION);
    public static final DateTimeFormatter FORMATTED_DATE_FOR_KYC = DateTimeFormatter.ofPattern(CLASSIC_SHORT_DATE_FORMAT);
    public static final DateTimeFormatter DATE_FORMAT_yyyy_MM_dd = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter YEAR_MONTH = DateTimeFormatter.ofPattern("yyyyMM");
    public static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("dd");
    private DateTimeUtils() {
    }

    public static synchronized long getCurrentUnixTimestamp() {
        return System.currentTimeMillis() / 1000L;
    }

    public static synchronized LocalDateTime dynamoDBFormatToLocalDateTime(String date) {
        return LocalDateTime.parse(date, LOCAL_DATE_DYNAMO_DB_FORMATTER).atZone(ZONE_ID_DHAKA).toLocalDateTime();
    }

    public static synchronized LocalDateTime convertUtcToBDTime(LocalDateTime localDateTime) {
        return localDateTime.atZone(DateTimeUtils.ZONE_ID_UTC).withZoneSameInstant(ZONE_ID_DHAKA).toLocalDateTime();
    }

    public static synchronized String currentTimeToFIAndCIMTFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat(FI_OR_CIMT_DATE_FORMAT);
        return sdf.format(new Date());
    }

    public static synchronized String currentTimeToDynamoDBFormat() {
        LocalDateTime localDateTime = currentDateTimeInDhakaTimeZone();
        return localDateTimeToDynamoDBFormat(localDateTime);
    }

    public static synchronized String currentTimeToShortDateFormat() {
        LocalDateTime localDateTime = currentDateTimeInDhakaTimeZone();
        return localDateTimeToShortDateFormat(localDateTime);
    }

    public static synchronized String currentTimeToClassicShortDateFormat() {
        LocalDateTime localDateTime = currentDateTimeInDhakaTimeZone();
        return localDateTimeToClassicShortDateFormat(localDateTime);
    }

    public static synchronized String localDateTimeToFIFormat(LocalDateTime localDateTime) {
        return localDateTime.format(LOCAL_DATE_FI_FORMATTER);
    }

    public static synchronized String localDateTimeToDynamoDBFormat(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZONE_ID_DHAKA);
        return zonedDateTime.format(LOCAL_DATE_DYNAMO_DB_FORMATTER);
    }

    public static synchronized String localDateTimeToShortDateFormat(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZONE_ID_DHAKA);
        return zonedDateTime.format(SHORT_DATE_FORMATTER);
    }

    public static synchronized String localDateTimeToClassicShortDateFormat(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZONE_ID_DHAKA);
        return zonedDateTime.format(CLASSIC_SHORT_DATE_FORMATTER);
    }

    public static synchronized String dynamoDBToBankOrCIMTFormat(String date) {
        if (StringUtils.isEmpty(date)) return "N/A";
        LocalDateTime localDateTime = LocalDateTime.parse(date, LOCAL_DATE_DYNAMO_DB_FORMATTER);
        return localDateTime.format(FI_OR_CIMT_DATE_FORMATTER);
    }

//    public static synchronized boolean isSameDayBasedOnDynamoDBFormat(String dynamoDBDateString) {
//        try {
//            String currentDateTime = currentTimeToDynamoDBFormat();
//            return currentDateTime.substring(0, 10).equals(dynamoDBDateString.substring(0, 10));
//        } catch (DateTimeParseException e) {
//            log.error("DateTimeUtils|isSameDayBasedOnDynamoDBFormat::Could not compare date", e);
//            throw new SystemException(e.getLocalizedMessage());
//        }
//    }

    public static synchronized boolean isWithinActiveRange(String fromDateString, String toDateString, DateTimeFormatter formatter) {
        LocalDate fromDate = LocalDate.parse(fromDateString, formatter);
        LocalDate toDate = null;
        if (toDateString != null && !toDateString.trim().isEmpty()) {
            toDate = LocalDate.parse(toDateString, formatter);
        }
        LocalDate currentDate = DateTimeUtils.currentDateTimeInDhakaTimeZone().toLocalDate();
        log.info("DateTimeUtils|isWithinActiveRange::After Conversion Current Date={}, From Date={}, To Date={}", currentDate, fromDate, toDate);
        if (toDate == null) {
            return currentDate.equals(fromDate) || currentDate.isAfter(fromDate);
        }
        return (currentDate.equals(fromDate) || currentDate.isAfter(fromDate)) && currentDate.isBefore(toDate);
    }

    private static synchronized SimpleDateFormat getDynamoDBDateFormatter(String pattern) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
        dateFormatter.setTimeZone(TimeZone.getTimeZone(ASIA_DHAKA_ZONE_ID));
        return dateFormatter;
    }

    public static synchronized String dbDateToNotificationDateFormat(String dateString) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, LOCAL_DATE_DYNAMO_DB_FORMATTER);
        return localDateTime.format(FORMATTED_DATE_FOR_NOTIFICATION);
    }

    public static synchronized String classicShortDateToFiFormat(String dateString) {
        if (StringUtils.isEmpty(dateString)) return "N/A";
        LocalDate localDate = LocalDate.parse(dateString, CLASSIC_SHORT_DATE_FORMATTER);
        return localDate.format(SHORT_DATE_FORMATTER);
    }

    public static synchronized String classicShortDateToDynamoDBFormat(String dateString) {
        return dateString + " 00:00:00:000 +0600";
    }

    public static synchronized String classicShortDateToFiFormatFromSimpleDate(String dateString) {
        LocalDate localDate = LocalDate.parse(dateString, CLASSIC_SHORT_DATE_FORMATTER);
        return localDate.format(SHORT_DATE_FORMATTER) + "000000";
    }

    public static synchronized String fiDateTimeToDynamoDBFormat(String fiDateTime) {
        if (StringUtils.isBlank(fiDateTime)) return "N/A";
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(fiDateTime, FI_OR_CIMT_DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new SavingsException(ApiResponseStatus.UNSUCCESSFUL.code(), "Date time format is not correct");
        }
        return localDateTimeToDynamoDBFormat(localDateTime);
    }

    public static synchronized LocalDateTime currentDateTimeInDhakaTimeZone() {
        return LocalDateTime.now().atZone(ZONE_ID_UTC).withZoneSameInstant(ZONE_ID_DHAKA).toLocalDateTime();
    }

//    public static synchronized void startDateAndEndDateUpdate(SubscriptionCreateDTO subscriptionCreateDTO, SavingsAccountEntity account, long bufferMinutes) {
//        String dynamoDBDateString = StringUtils.isEmpty(account.getCycleStartDate()) ?
//                getStartDateWhenCycleStartDateNotPresent(account.getStartDate(), account.getProduct().getTerm())
//                : classicShortDateToDynamoDBFormat(account.getCycleStartDate());
//        ZonedDateTime previousZonedDateTime = ZonedDateTime.parse(dynamoDBDateString, LOCAL_DATE_DYNAMO_DB_FORMATTER);
//        ZonedDateTime incrementedZonedDateTime = previousZonedDateTime.plusMinutes(bufferMinutes);
//        if (incrementedZonedDateTime.toLocalDate().isAfter(previousZonedDateTime.toLocalDate())) {
//            subscriptionCreateDTO.setStartDate(CLASSIC_SHORT_DATE_FORMATTER.format(incrementedZonedDateTime));
//            incrementEndDate(subscriptionCreateDTO, account, bufferMinutes);
//        } else {
//            subscriptionCreateDTO.setStartDate(CLASSIC_SHORT_DATE_FORMATTER.format(previousZonedDateTime));
//            incrementEndDate(subscriptionCreateDTO, account, 0);
//        }
//    }
//
//    private static synchronized void incrementEndDate(SubscriptionCreateDTO subscriptionCreateDTO, SavingsAccountEntity account, long bufferMinutes) {
//        String dynamoDBDateString = account.getEndDate();
//        ZonedDateTime previousZonedDateTime = ZonedDateTime.parse(dynamoDBDateString, LOCAL_DATE_DYNAMO_DB_FORMATTER);
//        ZonedDateTime incrementedZonedDateTime = previousZonedDateTime.plusMinutes(bufferMinutes);
//        incrementedZonedDateTime = extendEndDate(account, incrementedZonedDateTime);
//        subscriptionCreateDTO.setEndDate(CLASSIC_SHORT_DATE_FORMATTER.format(incrementedZonedDateTime));
//    }

    private static synchronized ZonedDateTime extendEndDate(SavingsAccountEntity account, ZonedDateTime incrementedZonedDateTime) {
        int daysToExtend = 0;
        String term = account.getProduct().getTerm();
        if (term.equals(Term.MONTHLY.name())) {
            daysToExtend = Term.MONTHLY.rppEndDateExtendedDays();
        } else if (term.equals(Term.BIWEEKLY.name())) {
            daysToExtend = Term.BIWEEKLY.rppEndDateExtendedDays();
        } else if (term.equals(Term.WEEKLY.name())) {
            daysToExtend = Term.WEEKLY.rppEndDateExtendedDays();
        }
        incrementedZonedDateTime = incrementedZonedDateTime.plusDays(daysToExtend);
        String incrementedExpiryTime = incrementedZonedDateTime.format(LOCAL_DATE_DYNAMO_DB_FORMATTER);
        ZonedDateTime maturityZonedDateTime = ZonedDateTime.parse(account.getMaturityDate(), LOCAL_DATE_DYNAMO_DB_FORMATTER).minusDays(1);
        String decrementedMaturityTime = maturityZonedDateTime.format(LOCAL_DATE_DYNAMO_DB_FORMATTER);
        if (incrementedExpiryTime.compareTo(decrementedMaturityTime) < 0) {
            return incrementedZonedDateTime;
        } else return maturityZonedDateTime;
    }

    public static synchronized boolean isValidQueryDate(String queryDate) {
        try {
            LocalDate localDate = LocalDate.parse(queryDate, DATE_FORMAT_yyyy_MM_dd);
            return !localDate.isAfter(currentDateTimeInDhakaTimeZone().toLocalDate());
        } catch (Exception e) {
            return false;
        }
    }

    public static synchronized boolean isValidQueryDateForBulkMaturity(String queryDate) {
        try {
            LocalDate.parse(queryDate, DATE_FORMAT_yyyy_MM_dd);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static synchronized int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    public static synchronized String getYearMonth() {
        LocalDate localDate = currentDateTimeInDhakaTimeZone().toLocalDate();
        return YEAR_MONTH.format(localDate);
    }

    public static synchronized String getDate() {
        LocalDate localDate = currentDateTimeInDhakaTimeZone().toLocalDate();
        return DATE.format(localDate);
    }

    public static synchronized int getAgeFromDateOfBirth(String dateOfBirth) {
        LocalDate dob = LocalDate.parse(dateOfBirth, FORMATTED_DATE_FOR_KYC);
        return DateTimeUtils.calculateAge(dob, currentDateTimeInDhakaTimeZone().toLocalDate());
    }

    public static synchronized String getStartDateWhenCycleStartDateNotPresent(String accountStartDate, String term) {
        int day = 0;
        int month = 0;
        if (term.equals(Term.MONTHLY.name())) month = 1;
        else if (term.equals(Term.BIWEEKLY.name())) day = 14;
        else if (term.equals(Term.WEEKLY.name())) day = 7;
        LocalDateTime localDateTime = dynamoDBFormatToLocalDateTime(accountStartDate).plusMonths(month).plusDays(day);
        return localDateTimeToDynamoDBFormat(localDateTime);
    }

    public static synchronized String gqlTimeToDynamoDBFormat(String dateTime) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, GQL_DATE_TIME_FORMATTER).atZone(ZONE_ID_DHAKA).toLocalDateTime();
        return localDateTimeToDynamoDBFormat(localDateTime);
    }

    public static synchronized LocalDate classicShortDateToLocalDate(String dateString) {
        return LocalDate.parse(dateString, CLASSIC_SHORT_DATE_FORMATTER);
    }

    public static synchronized LocalDate shortDateToLocalDate(String dateString) {
        return LocalDate.parse(dateString, SHORT_DATE_FORMATTER);
    }

    public static synchronized String classicShortDateToFiOrCimtFormat(String dateString) {
        if (StringUtils.isEmpty(dateString)) return "N/A";
        LocalDate localDate = LocalDate.parse(dateString, CLASSIC_SHORT_DATE_FORMATTER);
        return localDate.atStartOfDay().format(FI_OR_CIMT_DATE_FORMATTER);
    }

    public static synchronized String getCycleStartDate(String accountStartDate, Term term) {
        LocalDateTime baseTime = StringUtils.isBlank(accountStartDate) ?
                currentDateTimeInDhakaTimeZone() :
                dynamoDBFormatToLocalDateTime(accountStartDate);

        int day = 0, month = 0;
        switch (term) {
            case MONTHLY:
                month = 1;
                break;
            case WEEKLY:
                day = 7;
                break;
            default:
                day = 14;
                break;    //BI-WEEKLY
        }

        return localDateTimeToClassicShortDateFormat(baseTime.plusMonths(month).plusDays(day));
    }

    public static synchronized String fiDateTimeToClassicShortDate(String fiDateTime) {
        if (StringUtils.isBlank(fiDateTime)) return "";
        LocalDateTime localDateTime = LocalDateTime.parse(fiDateTime, FI_OR_CIMT_DATE_FORMATTER);
        return localDateTimeToClassicShortDateFormat(localDateTime);

    }

    public static synchronized boolean isValidCIMTOrFIDateFormat(String date) {
        DateFormat format = new SimpleDateFormat(FI_OR_CIMT_DATE_FORMAT);
        format.setLenient(false);
        try {
            format.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static synchronized String fiShortDateToFiOrCimtFullFormat(String dateString) {
        if (StringUtils.isEmpty(dateString)) return "N/A";
        return dateString.concat("000000");
    }
}
