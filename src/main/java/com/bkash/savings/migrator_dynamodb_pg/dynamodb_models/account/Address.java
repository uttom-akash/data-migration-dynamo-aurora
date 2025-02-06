package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.account;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {

    private String addressLine;

    private String postalCode;

    private String union;

    private String thanaOrUpazilla;

    private String cityOrDistrict;

    private String division;
}
