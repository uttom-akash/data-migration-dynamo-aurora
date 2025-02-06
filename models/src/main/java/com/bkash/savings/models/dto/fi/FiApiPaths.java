package com.bkash.savings.models.dto.fi;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FiApiPaths {

    MATURITY_DETAILS("maturity-details"),
    SEND_KYC("send-kyc"),
    CREATE_ACCOUNT("create-account"),
    NOMINEE_UPDATE("nominee-update"),
    SAVING_DETAILS("saving-details"),
    BANK_MATURITY("bank-maturity"),
    ACCOUNT_DELETION("account-deletion"),
    CANCEL_SUMMARY("cancel-summary"),
    SEND_E_TIN("send-etin"),
    BANK_SCHEDULE_PAYMENT("bank-schedule-payment"),
    CANCEL_CONFIRMATION("cancel-confirmation"),
    INSTALLMENT_PLAN("installment-plan"),
    INSTALMENT_PAY("instalment-pay"),
    REFUND_CONFIRMATION("refund-confirmation"),
    AUTO_RENEWAL_INFO("auto-renewal-info"),
    AUTO_RENEWAL_HISTORY("auto-renewal-hist"),
    BK_TO_FI_PUSH_TRX("push-transactions"),
    BK_TO_FI_QUERY_TRX("query-transactions"),
    BK_TO_FI_PULL_ACCOUNT_DETAILS("get-batch-details");

    private final String key;

}
