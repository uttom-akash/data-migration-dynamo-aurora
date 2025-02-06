package com.bkash.savings.models.dto.fi.v1.response;

import lombok.*;
import org.json.JSONObject;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BulkPaymentOldDataDTO implements Serializable {

    private String accountNo;
    private String trxId;
    private String amount;
    private String trxDate;

    JSONObject jsonObject() {
        JSONObject object = new JSONObject();
        object.put("accountNo", this.accountNo);
        object.put("trxId", this.trxId);
        object.put("amount", this.amount);
        object.put("trxDate", this.trxDate);
        return object;
    }

}
