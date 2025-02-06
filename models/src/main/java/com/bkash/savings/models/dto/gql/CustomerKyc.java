package com.bkash.savings.models.dto.gql;

import java.io.Serializable;

public record CustomerKyc(
        KycData data
) implements Serializable {

    public record KycData(GetKycInfo getKycInfo) {
    }

    public record GetKycInfo(
            String msisdn,
            String fullName,
            String mothersName,
            String fathersName,
            String gender,
            String dateOfBirth,
            String profession,
            Address address,
            String nid
    ) implements Serializable {

        public record Address(
                String addressLine,
                String postalCode,
                String union,
                String thanaOrUpazilla,
                String cityOrDistrict
        ) implements Serializable {
        }
    }
}
