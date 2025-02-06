package org.dynamo.models;

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
    public static final String GSI_WALLET_NO = "gsi-walletNo";
    public static final String GSI_TRX_SHORT_DATE = "gsi-trxShortDate";
    private DynamoDBIndexes() {
    }
}
