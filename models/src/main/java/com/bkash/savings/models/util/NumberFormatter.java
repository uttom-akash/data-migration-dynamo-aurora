package com.bkash.savings.models.util;

import com.bkash.savings.models.common.ApiResponseStatus;
import com.bkash.savings.models.exception.SavingsException;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class NumberFormatter {

    public static final String decimalFormat = "#0.00";

    
    public static Double inSavingsFormat(Double number) {
        if (number == null) {
            return 0.0;
        }
        DecimalFormat df = new DecimalFormat(decimalFormat);
        String formattedNumber = df.format(number);
        return Double.parseDouble(formattedNumber);
    }

    public static String inSavingsFormat(String stringNumber) {
        if (StringUtils.isEmpty(stringNumber)) {
            return "0.0";
        }
        Double number = Double.parseDouble(stringNumber);
        DecimalFormat df = new DecimalFormat(decimalFormat);
        return df.format(number);
    }

    public static Double toSavingsFormat(String stringNumber) {
        if (StringUtils.isEmpty(stringNumber)) {
            return 0.0;
        }
        Double number = Double.parseDouble(stringNumber);
        DecimalFormat df = new DecimalFormat(decimalFormat);
        String formattedNumber = df.format(number);
        return Double.parseDouble(formattedNumber);
    }

    /**
     * This method will firstly check the inputted string is a number or not. If it is a number, <br>
     * then the method will decide whether it is a fractional number or not. If it is a fractional number<br>
     * it will just returns the number before decimal point, otherwise it will return thee inputted number.<br><br>
     * <p>
     * e.g. <br>
     * Input "10.00" -> Output "10" <br>
     * Input "10.80" -> Output "10" <br>
     * Input "10" -> Output "10" <br>
     * Input null -> throws {@link SavingsException} <br>
     * Input ""  -> throws {@link SavingsException} <br>
     * Input "23FRGk"  -> throws {@link SavingsException} <br>
     *
     * @param input {@link String}
     * @return rounded {@link String}
     */
    public static String floor(String input) {
        if (!isNumeric(input))
            throw new SavingsException(ApiResponseStatus.ARGUMENT_NOT_VALID.code(), "Non numeric input can not be rounded");
        if (input.contains(".")) input = input.split("\\.")[0];
        return input;
    }

    public static BigDecimal floor(@NonNull BigDecimal input) {
        return input.setScale(0, RoundingMode.DOWN);
    }

    /**
     * Checks whether the given inputted string is a number or not. Fractional number also returns true.
     *
     * @param input
     * @return {@link Boolean}
     */
    public static boolean isNumeric(String input) {
        if (StringUtils.isEmpty(input)) return false;
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        return pattern.matcher(input).matches();
    }
}
