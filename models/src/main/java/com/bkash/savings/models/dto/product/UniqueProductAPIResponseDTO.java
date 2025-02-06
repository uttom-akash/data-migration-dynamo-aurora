package com.bkash.savings.models.dto.product;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UniqueProductAPIResponseDTO implements Serializable {

    private String organizationCode;

    private String organizationType;

    private String organizationName;

    private String organizationLogo;

    private String termsAndConditionsUrl;

    private String productType;

    private Double interest;
}
