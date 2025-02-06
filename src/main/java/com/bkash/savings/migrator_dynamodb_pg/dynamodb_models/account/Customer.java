package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.account;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {

    private String applicantName;
    private String fatherName;
    private String motherName;
    private String dob;
    private String walletNo;
    private String gender;
    private String nidNumber;
    private String profession;
    private Address address;

}
