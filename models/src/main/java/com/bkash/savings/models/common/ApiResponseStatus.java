package com.bkash.savings.models.common;

public enum ApiResponseStatus {

    SUCCESSFUL("SAVINGS_1000", "Operation successful"),
    UNSUCCESSFUL("SAVINGS_1001", "Operation unsuccessful"),
    ARGUMENT_NOT_VALID("SAVINGS_1002", "One of the arguments is not valid"),
    UNAUTHORIZED_SERVICE("SAVINGS_4001", "Unauthorized Service"),
    INTERNAL_ERROR("SAVINGS_1003", "Something went wrong"),
    FI_TIMEOUT("SAVINGS_2408", "Bank/NBFI resource read timeout"),
    KYC_TIMEOUT("SAVINGS_3408", "KYC resource read timeout"),
    KYC_CONNECT_FAILED("SAVINGS_3408", "KYC connect failed"),
    ORGANIZATION_NOT_FOUND("SAVINGS_1204", "Organization not found"),
    ORGANIZATION_INACTIVE("SAVINGS_1205", "Organization is not active"),
    NOT_FOUND("SAVINGS_1404", "Requested resource not found"),
    NOT_VALID("SAVINGS_7777", "Request not valid"),
    NOT_PERMITTED("SAVINGS_7403", "Operation not permitted"),
    NOT_PROCESSED("SAVINGS_3409", "Operation not yet processed"),
    NOTIFICATION_SEND_FAILED("SAVINGS_1405", "Notification send failed"),
    ALREADY_EXISTS("SAVINGS_1409", "Resource already exists"),
    ALREADY_PROCESSING("SAVINGS_1410", "Task already processing"),
    SUBSCRIPTION_ALREADY_ACTIVE("SAVINGS_9409", "Subscription already active"),
    SUBSCRIPTION_CREATE_FAILED("SAVINGS_1412", "Subscription create failed"),
    KYC_IS_NOT_VALID("SAVINGS_1413", "KYC information is not valid"),
    SAVINGS_ACCOUNT_NOT_FOUND("SAVINGS_2404", "Savings account not found"),
    NOMINEE_NOT_FOUND("SAVINGS_2404", "Nominee account not found"),
    PRODUCT_VALIDATION_FAILED("SAVINGS_3203", "Product validation failed"),
    PRODUCT_NOT_FOUND("SAVINGS_3204", "Product not found"),
    PRODUCT_NOT_ACTIVE("SAVINGS_3205", "Product not active"),
    ACCOUNT_NOT_ACTIVE("SAVINGS_1403", "Savings account not active"),
    ACCOUNT_NOT_ELIGIBLE("SAVINGS_1403", "Savings account not eligible for this service"),
    ACCOUNT_INIT_FAILED("SAVINGS_1406", "Savings account init failed"),
    ETIN_NOT_FOUND("SAVINGS_3404", "eTIN not found"),
    AUTHORIZATION_DETAILS_NOT_FOUND("SAVINGS_4204", "Notification authorization details not found"),
    NOTIFICATION_EVENT_NOT_FOUND("SAVINGS_4404", "Notification event not found"),
    BAD_REQUEST("SAVINGS_4400", "Bad request"),
    UNKNOWN_ERROR("SAVINGS_4411", "Unknown error occurred"),
    REQUEST_PAYLOAD_INVALID("SAVINGS_5400", "Request Payload is not valid"),
    DUE_DATE_OR_AMOUNT_INVALID("SAVINGS_5401", "Due date or Amount is not valid"),
    WRONG_INSTALLMENT_STATUS_OR_ALREADY_PAID("SAVINGS_5402", "Payment is not Missed or exist in our DB"),
    WRONG_INSTALLMENT_QUERY_INPUT("SAVINGS_5403", "Inputted information does not match with any installment plan"),
    INSTALLMENT_PAY_IS_NOT_MISSED("SAVINGS_54054", "Installment is not missed yet"),

    ACCOUNT_MATURITY_LESS_THAN_MATURITY_TARGET_DAYS("SAVINGS_5404", "Maturity date is closer than the eligibility of missed payment."),
    //fi
    FI_SEND_KYC_FAILED("SAVINGS_1500", "KYC not sent"),
    FI_CREATE_ACCOUNT_FAILED("SAVINGS_1501", "Create account failed"),
    FI_CANCEL_ACCOUNT_FAILED("SAVINGS_1502", "Cancel account failed"),
    FI_MATURITY_DETAILS_FAILED("SAVINGS_1503", "Maturity details fetch failed"),
    FI_NOMINEE_UPDATE_FAILED("SAVINGS_1504", "Nominee update failed"),
    FI_SAVING_DETAILS_FAILED("SAVINGS_1505", "Saving details failed"),
    FI_AUTO_RENEWAL_HISTORY_FETCH_FAILED("SAVINGS_1506", "Auto renewal history fetch failed"),
    FI_AUTO_RENEWAL_INFO_FETCH_FAILED("SAVINGS_1514", "Auto renewal info fetch failed"),
    FI_SAVINGS_ACCOUNT_INFORMATION_FETCH_FAILED("SAVINGS_1507", "Savings account information fetch failed"),
    FI_CANCEL_SUMMARY_FAILED("SAVINGS_1506", "Cancel Summary failed"),
    FI_ACCOUNT_DELETION_FAILED("SAVINGS_1507", "Account deletion failed"),
    FI_SEND_ETIN_FAILED("SAVINGS_1508", "Send eTIN failed"),
    FI_MATURITY_BULK_FAILED("SAVINGS_1509", "Maturity bulk failed"),
    FI_SCHEDULE_PAYMENT_FAILED("SAVINGS_1510", "Schedule payment failed"),
    FI_INSTALLMENT_PLAN_FAILED("SAVINGS_1511", "Installment plan failed"),
    FI_INSTALLMENT_PAY_FAILED("SAVINGS_1511", "Installment pay failed"),
    FI_REFUND_CONFIRMATION_FAILED("SAVINGS_1512", "Refund confirmation failed"),
    FI_API_PATH_NOT_FOUND("SAVINGS_1513", "FI API path not found"),
    FI_CREATE_ACCOUNT_ACCEPTED("SAVINGS_1202", "Fi accepted create account request"),

    //rps
    RPS_SUBSCRIPTION_CREATE_REQUEST_FAILED("SAVINGS_9000", "Subscription creation failed"),
    RPS_SUBSCRIPTION_CANCEL_REQUEST_FAILED("SAVINGS_9001", "Subscription cancellation failed"),
    RPS_SUBSCRIPTION_QUERY_REQUEST_FAILED("SAVINGS_9002", "Subscription status query failed"),
    RPS_SUBSCRIPTION_TASK_CREATE_FAILED("SAVINGS_9003", "Subscription task creation failed"),
    RPS_SUBSCRIPTION_TASK_NOT_FOUND("SAVINGS_9004", "Subscription task not found"),
    RPS_SUBSCRIPTION_TASK_UPDATE_FAILED("SAVINGS_9005", "Subscription task update failed"),
    RPS_PAYMENT_QUERY_REQUEST_FAILED("SAVINGS_9006", "Payment status query failed"),
    RPS_TABLE_UPDATE_FAILED("SAVINGS_9007", "Table update failed"),

    //rpp
    RPP_SUBSCRIPTION_REQUEST_FAILED("SAVINGS_1600", "Subscription request not sent"),
    RPP_UNSUBSCRIPTION_REQUEST_FAILED("SAVINGS_1601", "RPP unsubscription request failed"),
    RPP_REFUND_REQUEST_FAILED("SAVINGS_1602", "Refund request not sent"),
    RPP_SUBSCRIPTION_FAILED("SAVINGS_1603", "Subscription failed at RPP"),
    RPP_SUBSCRIPTION_PENDING("SAVINGS_1604", "Subscription pending at RPP"),
    RPP_API_ERROR("SAVINGS_1605", "RPP API endpoint or request payload not valid"),
    RPP_TIMEOUT("SAVINGS_1606", "RPP resource read timeout"),
    RPP_SUBSCRIPTION_ALREADY_CANCELLED("SAVINGS_1607", "Subscription already cancelled at RPP"),


    //webhook
    RPP_SUBSCRIPTION_WEBHOOK_REQUEST_FAILED("SAVINGS_1700", "Subscription webhook request failed"),
    RPP_UNSUBSCRIPTION_WEBHOOK_REQUEST_FAILED("SAVINGS_1701", "Unsubscription webhook request failed"),
    RPP_SUBSCRIPTION_WEBHOOK_REQUEST_PAYLOAD_INVALID("SAVINGS_1702", "Subscription webhook request payload not valid"),
    RPP_SUBSCRIPTION_FAILED_WEBHOOK_REQUEST_PAYLOAD_INVALID("SAVINGS_1703", "Subscription failed webhook request payload not valid"),
    RPP_PAYMENT_WEBHOOK_REQUEST_PAYLOAD_INVALID("SAVINGS_1704", "Payment webhook request payload not valid"),
    RPP_REFUND_WEBHOOK_REQUEST_PAYLOAD_INVALID("SAVINGS_1705", "Refund webhook request payload not valid"),
    SETTLEMENT_REQUIRED_UPDATE_FAILED("SAVINGS_1777", "Settlement required update failed"),

    //dynamodb
    DYNAMODB_READ_OR_WRITE_FAILED("SAVINGS_9000", "Read or write failed at DynamoDB"),

    //kyc
    FETCH_KYC_REQUEST_FAILED("SAVINGS_1800", "Fetch kyc request failed"),
    FETCH_KYC_FAILED("SAVINGS_1801", "Fetch kyc failed"),
    EKYC_INFORMATION_IS_NOT_FULFILLED("SAVINGS_1060", "eKYC information is not fulfilled"),
    WALLET_NOT_BELONG_TO_A_CUSTOMER("SAVINGS_1061", "Wallet not belong to a customer"),

    API_KEY_VALIDATION_FAILED("SAVINGS_1900", "API key validation failed"),
    SIGNATURE_VALIDATION_FAILED("SAVINGS_1901", "Signature validation failed"),
    SIGNATURE_GENERATION_FAILED("SAVINGS_1902", "Signature generation failed"),

    // eTin
    ETIN_REQUEST_VALIDATION_FAILED("SAVINGS_1422", "eTIN not valid, it must be a 12 digit number"),

    NOMINEE_VALIDATION_FAILED("SAVINGS_2422", "Customer and Nominee NID must not be same"),

    NOMINEE_CREATION_FAILED("SAVINGS_2427", "Nominee NID is already present"),
    NOMINEE_UPDATE_FAILED("SAVINGS_2428", "Nominee update failed"),
    NOMINEE_AGE_VALIDATION_FAILED("SAVINGS_2429", "Nominee age must be at least 18 years"),
    CUSTOMER_AGE_VALIDATION_FAILED("SAVINGS_2423", "Customer age must be at least 18 years"),
    CUSTOMER_KYC_VALIDATION_FAILED("SAVINGS_2424", "Customer age or NID information not valid"),
    DATE_FORMAT_IS_NOT_CORRECT("SAVINGS_2425", "Provided date format is not correct"),
    CYCLE_START_DATE_IS_INVALID("SAVINGS_2426", "Cycle start date is invalid"),
    //payment
    REFUNDED("SAVINGS_2100", "Refund successful"),
    NOT_REFUNDABLE("SAVINGS_2104", "Not refundable"),

    // account cancellability
    NOT_CANCELABLE("SAVINGS_2208", "Account not cancelable"),

    // subscription cancellability
    CANCELLABILITY_CANCELLABLE("SAVINGS_2200", "User can cancel the subscription"),
    CANCELLABILITY_ERROR_INACTIVE("SAVINGS_2201", "Savings account not active"),
    CANCELLABILITY_ERROR_CANCELLED("SAVINGS_2202", "Savings account already cancelled"),
    CANCELLABILITY_ERROR_MATURED("SAVINGS_2203", "Account already matured"),
    CANCELLABILITY_ERROR_DISBURSED("SAVINGS_2204", "Amount already disbursed"),
    CANCELLABILITY_ERROR_MIN_PAY_CYCLES_NOT_ELAPSED("SAVINGS_2205", "Minimum payment cycles not elapsed"),
    CANCELLABILITY_ERROR_UNKNOWN("SAVINGS_2206", "There was a problem, please try later"),
    CANCELLABILITY_ERROR_FAILED("SAVINGS_2207", "Account status is failed"),
    ACCOUNT_ID_IS_NULL("SAVINGS_2500", "Account ID is null"),
    ACCOUNT_ALREADY_CANCELLED("SAVINGS_25490", "Account already cancelled"),
    PASSWORD_MISMATCH("SAVINGS_6666", "Wrong password. Old password should be your existing password."),

    //BI
    BI_API_ERROR("SAVINGS_3333", "Error occurred while calling BI API"),
    BI_API_NULL_RESPONSE("SAVINGS_3334", "Response is null"),
    BI_GQL_TIMEOUT("SAVINGS_1408", "GQL resource read timeout"),
    BI_GQL_CONNECT_FAILED("SAVINGS_1411", "GQL connect failed"),


    //CPS
    CPS_API_ERROR("SAVINGS_4444", "Error occurred while calling CPS Proxy API"),
    CPS_PAYMENT_FAILED("SAVINGS_4445", "CPS payment failed"),
    CPS_TIMEOUT("SAVINGS_4446", "CPS timeout"),
    CPS_PAYMENT_PENDING("SAVINGS_4447", "CPS payment pending"),

    // migration hash
    MIGRATION_HASH_NOT_FOUND("SAVINGS_2301", "Migration hash not found"),
    SQS_PROCESS_EXCEPTION("SAVINGS_9999", "An exception was thrown during the execution of the SQS listener method and Message will be still available in Queue"),

    PAYMENT_NOT_FOUND("SAVINGS_9404", "Payment not found with given information"),


    //assisted savings

    ASSISTED_LIST_FETCH_FAILED("SAVINGS_6801", "Assisted savings list fetch failed"),
    ASSISTED_FILTER_YEAR_MONTH_VALIDATION("SAVINGS_6802", "Month and year must either both be provided or both be empty."),
    ASSISTED_SAVINGS_INFO_MISMATCH("SAVINGS_6803", "Assisted information not matching"),
    ASSISTED_TRANSACTION_NOT_FOUND("SAVINGS_6814", "Assisted transaction not found"),
    ASSISTED_SAVINGS_NOT_FOUND("SAVINGS_6804", "Assisted information not found or fetch failed"),
    ASSISTED_DUPLICATE_WALLET_NO("SAVINGS_6809", "Requester and customer wallet number cannot be same"),
    ASSISTED_REQUEST_ALREADY_INITIATED("SAVINGS_6819", "Assisted request is already initiated"),

    //scheduler
    ACCOUNT_MATURITY_UPDATE_FAILED("SAVINGS_6901", "Maturity update failed for some accounts"),

    ACTIVE_ACCOUNT_CREATION_LIMIT_EXCEEDED("SAVINGS_6902", "Active account creation limit exceeded"),

    DUPLICATE_DISBURSEMENT("SAVIINGS_6903", "Dupplicate Disbursement Attempt is not Allowed"),

    FI_TRANSACTION_SYNC_ERROR("SAVIINGS_6904", "Transaction Sync up with FI is failed"),

    BK_TO_FI_PULL_ACCOUNT_DETAILS_ERROR("SAVIINGS_6905", "Pulling account details from FI is failed"),

    TRANSACTION_SYNC_ERROR("SAVIINGS_6906", "Transaction Sync up is failed"),
    
    NOMINEE_NID_ALREADY_EXIST("SAVINGS_6907", "Nominee NID already exist");

    private final String code;
    private final String message;

    ApiResponseStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }
}