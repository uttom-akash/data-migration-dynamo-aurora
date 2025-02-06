package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.common.util;

public class DynamoDBIndexes {
    //local indexes
    public static final String LSI_FI_ACCOUNT_NO = "lsi-fiAccountNo";
    public static final String LSI_FI_ACCOUNT_ID = "lsi-fiAccountId";
    public static final String LSI_RPP_PAYMENT_ID = "lsi-rppPaymentId";
    public static final String LSI_REQUESTER_WALLET_ID_SAVINGS_TYPE = "lsi-requesterWalletId-savingsType";
    public static final String LSI_REQUESTER_WALLET_ID_ORGANIZATION_CODE = "lsi-requesterWalletId-organizationCode";
    public static final String LSI_REQUESTER_WALLET_ID_STATUS = "lsi-requesterWalletId-status";
    public static final String LSI_REQUESTER_WALLET_ID_REQUEST_CREATE_TIME = "lsi-requesterWalletId-requestCreateTime";
    //global indexes
    public static final String GSI_SUBSCRIPTION_REQUEST_ID = "gsi-subscriptionRequestId";
    public static final String GSI_RPP_SUBSCRIPTION_REQUEST_ID = "gsi-rppSubscriptionRequestId";
    public static final String GSI_WALLET_NO = "gsi-walletNo";
    public static final String GSI_MATURITY_SHORT_DATE = "gsi-maturityShortDate";
    public static final String GSI_START_SHORT_DATE_CURRENT_STATE = "gsi-startShortDate-currentState";
    public static final String GSI_TRX_SHORT_DATE = "gsi-trxShortDate";
    public static final String GSI_TRX_ID = "gsi-trxId";
    public static final String GSI_ETIN_WALLET_NO = "gsi-etin-walletNo";
    public static final String GSI_CUSTOMER_WALLET_ID_REQUEST_ID = "gsi-customerWalletId-requestId";
    private DynamoDBIndexes() {
    }
}
