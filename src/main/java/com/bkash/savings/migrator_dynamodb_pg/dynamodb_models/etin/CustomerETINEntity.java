package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.etin;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.ToString;

@ToString
@DynamoDBTable(tableName = "customer-etin")
public class CustomerETINEntity {

    // user wallet number
    @DynamoDBHashKey
    private String walletId;

    private String walletNo;

    // etin number
    private String etin;

    // etin created time
    private String etinCreatedTime;

    // etin updated time
    private String etinUpdatedTime;

    @DynamoDBAttribute
    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    @DynamoDBAttribute
    public String getEtin() {
        return etin;
    }

    public void setEtin(String etin) {
        this.etin = etin;
    }

    @DynamoDBAttribute
    public String getEtinCreatedTime() {
        return etinCreatedTime;
    }

    public void setEtinCreatedTime(String etinCreatedTime) {
        this.etinCreatedTime = etinCreatedTime;
    }

    @DynamoDBAttribute
    public String getEtinUpdatedTime() {
        return etinUpdatedTime;
    }

    public void setEtinUpdatedTime(String etinUpdatedTime) {
        this.etinUpdatedTime = etinUpdatedTime;
    }

    @DynamoDBAttribute
    public String getWalletNo() {
        return walletNo;
    }

    public void setWalletNo(String walletNo) {
        this.walletNo = walletNo;
    }
}
