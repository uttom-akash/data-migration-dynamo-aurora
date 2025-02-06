package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.etin;

public interface BankETINService {

    void sendBankETIN(String walletNo, String etin);
}