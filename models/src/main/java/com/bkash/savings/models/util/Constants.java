package com.bkash.savings.models.util;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.bkash.savings.models.common.ApiResponseStatus;
import com.bkash.savings.models.exception.SavingsException;

public class Constants {

    private Constants() {}

    public static final String DATE_yyyy_MM_dd_hh_mm_a = "yyyy/MM/dd hh:mm a";
    public static final String TIMESTAMP = "timestamp";
    public static final String PRODUCT_TYPE_KEY = "product-types";
    public static final String MATURITY_SUMMARY_KEY = "maturity-summary";
    public static final String PRODUCTS_KEY = "product-schemes";
    public static final String SUBSCRIPTION_KEY = "subscription";
    public static final String UNSUBSCRIPTION_KEY = "unsubscription";
    public static final String SUBSCRIPTIONS_KEY = "subscriptions";
    public static final String ACCOUNT_NO_KEY = "account-no";
    public static final String CANCEL_SUMMARY_KEY = "cancel-summary";

    public static final String MIDDLEWARE_APP_ID = "middleware_app";
    public static final String WEB_ADMIN_APP_ID = "web_admin_app";
    public static final String ETIN_KEY = "etin";
    public static final String SEND_KYC_KEY = "kyc";
    public static final String SAVING_DETAILS = "saving-details";
    public static final String SAVINGS = "SAVINGS";
    public static final int SAVINGS_LOWER_LIMIT = 100100;

    public static final String ASSISTED_SAVINGS = "ASSISTED_SAVINGS";
    public static final int ASSISTED_SAVINGS_LOWER_LIMIT = 1000100;
    public static final String SAVINGS_MW = "saving-mw";
    public static final String X_B3_TRACE_ID = "X-B3-TraceId";
    public static final String TRACE_ID = "traceId";
    public static final String HEADERS = "headers";
    public static final String PAYLOAD = "payload";
    public static final String SUBSCRIPTION_API = "/subscription";
    public static final String SUBSCRIPTION_CANCELLATION_API = "/subscription/cancel/";
    public static final String STATUS_QUERY_BY_RPP_SUBSCRIPTION_REQUEST_ID_API = "/subscriptions/request-id/";
    public static final String REFUND_API = "/subscription/payment/refund";
    public static final String PAYMENT_BY_SUBSCRIPTION_ID_API = "/subscription/payment/bySubscriptionId/";
    public static final String USER_NOT_VERSION_COMPATIBLE = "User Not Notification Version Compatible";
    public static final String API_PATH_KEY_VALUE = "apiPathKey";
    public static final String API_PATHS_KEY = "-api-paths";
    public static final String API_PATHS_KEY_V2 = "-api-paths-v2";
    public static final String NOTIFICATION_EVENT = "notification-event";
    public static final String NOTIFICATION_AUTHORIZATION = "notification-authorization";
    public static final String AGENT_NOTIFICATION_AUTHORIZATION = "agent-notification-authorization";
    public static final String DEFAULT_PURPOSE = "FUTURE_NEED";
    public static final String BULK_SCHEDULE_PAYMENT_DISPUTED_DATA = "SavingsMW_BulkPaymentDisputedData";


    public static final String BKASH = "bKash";
    public static final String USER = "User";
    public static final String FI = "FI";
    public static final String FI_ACCOUNT_ALREADY_CREATED_CODE = "1112";
    public static final String X_API_KEY = "x-api-key";
    public static final String X_SERVICE_API_KEY = "X-SERVICE-API-KEY";
    public static final String SIGNATURE = "signature";
    public static final String SECRET_KEY = "secret-key";

    public static final String AES_ALGORITHM = "AES";
    public static final String MISSED_PAYMENT = "MissedPayment";

    //swagger API tags
    public static final String SWAGGER_API_TAG_ACCOUNT_APP = "Customer Application API";
    public static final String SWAGGER_API_TAG_SAVINGS_ASSISTED = "Savings Assisted API";
    public static final String SWAGGER_API_TAG_INTERNAL = "Internal API";
    public static final String SWAGGER_API_TAG_RPP = "RPP API";
    public static final String SWAGGER_API_TAG_FI_OR_BANK = "FI or Bank API";
    public static final String SWAGGER_TEST_API_TAG_FI_OR_BANK = "FI or Bank Test API";
    public static final String SWAGGER_API_TAG_GENERAL = "General API";
    public static final String SWAGGER_API_TAG_CIMT = "CIMT API";

    public static final String EXPOSER = "exposer";
    public static final String VERSION = "version";

    public static final String ORG_CODE = "orgCode";
    public static final String DATA = "data";
    public static final String ACCOUNT_NO = "accountNo";

    public static final String CIMT = "CIMT";
    public static final String CIMT_PASSWORD = "cimt-password";
    public static final String AES_SECRET_KEY = "aes-secret-key";
    public static final String X_API_KEY_RPP = "x-api-key";
    public static final String AZURE_CLIENT_ID = "azure-client-id";
    public static final String AZURE_CLIENT_SECRET = "azure-client-secret";
    public static final String AZURE_TENANT_ID = "azure-tenant-id";
    public static final String CHANNEL_ID = "channelId";
    public static final String RPP_TIME_STAMP = "timeStamp";

    public static Object getKeyMappedObject(String key, Object value) {
        return Map.of(key, value);
    }

    public static final String SELECTED_EVENTS = "selectedEvents";

    public static final String RPP_NOT_SUCCESSFUL_MESSAGE = "RPP response is not successful";
    public static final String RPP_SUBSCRIPTION_NOT_FOUND = "Rpp subscription not found";
    public static final String HTTP_ERROR_CONNECTING_TO_RPP = "HTTP Error connecting to rpp";

    public static final String RPP_LOG_EXCEPTION_STATUS_CODE = "Exception|ConsumerInfo=RPS|Service=(*/*)|Status Code = {}";
    public static final String RPP_LOG_EXCEPTION_HTTP_ERROR_CONNECTING = "Exception|ConsumerInfo=RPS|Service=(*/*)|HTTP Error connecting to rpp";
    public static final String RPP_LOG_EXCEPTION_HTTP_ERROR_CONNECTING_MESSAGE = "HTTP Error connecting to RPP : {}";

    public static final String REASON = "reason";
    public static final String ERROR_CODE = "errorCode";
    public static final String RPP_SUBSCRIPTION_ALREADY_CANCELLED_CODE = "2010";
    public static final String RPP_SUBSCRIPTION_NOT_FOUND_CODE = "2009";
    public static final String RPP_REFUND_NOT_POSSIBLE_CODE = "2011";
    public static final String RPP_NOT_REACHABLE = "RPP_NOT_REACHABLE";

    public static final String VERSION_1 = "1";
    public static final String VERSION_2 = "2";
    public static final String CPS_PAYMENT_FAILED = "CPS Payment Failed";

    public static final String CPS_INITIATOR_IDENTIFIER_TYPE = "14";
    public static final String CPS_PAYMENT_COMPLETED = "Completed";

    public static final String LAST_EVAL_KEY = "lastEvaluatedKey";
    public static final String START_SHORT_DATE = "startShortDate";
    public static final String WALLET_ID = "walletId";
    public static final String CURRENT_STATE = "currentState";
    public static final String SAVINGS_ID = "savingsId";
    public static final String PAYMENT_ID = "paymentId";
    public static final String MATURITY_SHORT_DATE = "maturityShortDate";
    public static final String ORGANIZATION_CODE = "organizationCode";
    public static final String PRODUCT_ID = "productId";

    public static final String FI_V2_API = "fiV2Api";
    public static final String FI_V1_API = "fiV1Api";

    public static final String CUSTOMER_IDENTITY_TYPE = "1000";
    public static final String AGENT_IDENTITY_TYPE = "5000";
    public static final String MERCHANT_IDENTITY_TYPE = "5000";

    public static final String MINIMUM_APP_VERSION = "5.8.0";

    public static final String decimalFormat = "#0.00";

    public static final String RESTRICTIONS = "restrictions";
    
    public static final String ORG_CODE_HEADER = "orgCode";
    public static final String CAPP_VERSION = "X-CAPP-VERSION";

    public static String getRppSignature() {
        return "k2!h3kxn12345";
    }

    /**
     * moved to {@link NumberFormatter}
     *
     * @param number
     * @return
     */
    @Deprecated
    public static Double inSavingsFormat(Double number) {
        if (number == null) {
            return 0.0;
        }
        DecimalFormat df = new DecimalFormat(decimalFormat);
        String formattedNumber = df.format(number);
        return Double.parseDouble(formattedNumber);
    }

    /**
     * moved to {@link NumberFormatter}
     */
    @Deprecated
    public static String inSavingsFormat(String stringNumber) {
        if (StringUtils.isEmpty(stringNumber)) {
            return "0.0";
        }
        Double number = Double.parseDouble(stringNumber);
        DecimalFormat df = new DecimalFormat(decimalFormat);
        return df.format(number);
    }

    /**
     * moved to {@link NumberFormatter}
     */
    @Deprecated
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
     *
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
    
    /**
     * moved to {@link NumberFormatter}
     */
    @Deprecated
    public static String floor(String input) {
        if (!isNumeric(input)) throw new SavingsException(ApiResponseStatus.ARGUMENT_NOT_VALID.code(), "Non numeric input can not be rounded");
        if (input.contains(".")) input = input.split("\\.")[0];
        return input;
    }

    /**
     * Checks whether the given inputted string is a number or not. Fractional number also returns true.
     *
     * @param input
     * @return {@link Boolean}
     */

    /**
     * moved to {@link NumberFormatter}
     */
    @Deprecated
    public static boolean isNumeric(String input) {
        if (StringUtils.isEmpty(input)) return false;
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        return pattern.matcher(input).matches();
    }
}
