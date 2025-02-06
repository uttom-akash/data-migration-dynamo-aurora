package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.findrequesttype;

import lombok.Getter;

@Getter
public enum ServiceRequesterType {

    CAPP,
    MAPP,
    AAPP,
    GIVE_ALL;
}
