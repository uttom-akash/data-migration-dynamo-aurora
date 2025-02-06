package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.etin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Etin implements Serializable {
    // user wallet number
    private String walletNo;

    // etin number
    private String etin;

    // etin created time
    private String etinCreatedTime;

    // etin updated time
    private String etinUpdatedTime;

    @JsonIgnore
    public EtinEntity toEntity() {
        EtinEntity etinEntity = new EtinEntity();

        etinEntity.setWalletNo(walletNo);
        etinEntity.setEtin(etin);
        etinEntity.setEtinCreatedTime(etinCreatedTime);
        etinEntity.setEtinUpdatedTime(etinUpdatedTime);

        return etinEntity;
    }
}
