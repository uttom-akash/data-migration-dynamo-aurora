package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.etin;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Data
@Getter
@Setter
@DynamoDBTable(tableName = "etin")
public class EtinEntity {

    // user wallet number
    @DynamoDBHashKey
    private String walletNo;

    // etin number
    private String etin;

    // etin created time
    private String etinCreatedTime;

    // etin updated time
    private String etinUpdatedTime;

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

    public boolean equalCustomerEtin(CustomerETINEntity customerETIN) {

        String etinString = this.getWalletNo()
                + this.getEtin()
                + this.getEtinCreatedTime()
                + this.getEtinUpdatedTime();
        String customerEtinString = customerETIN.getWalletNo()
                + customerETIN.getEtin()
                + customerETIN.getEtinCreatedTime()
                + customerETIN.getEtinUpdatedTime();

        return etinString.equals(customerEtinString);
    }

    public Etin toEtin() {
        Etin eTin = new Etin();

        eTin.setWalletNo(walletNo);
        eTin.setEtin(etin);
        eTin.setEtinCreatedTime(etinCreatedTime);
        eTin.setEtinUpdatedTime(etinUpdatedTime);

        return eTin;
    }
}
