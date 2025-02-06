package com.bkash.savings.models.dto.product;

import java.util.List;

import com.bkash.savings.models.postgres.product.Term;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductResponseDTO {

    private String organizationCode;

    private String productId;

    private String productType;

    private String interest;

    private String tenure;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Term term;

    private String amount;

    private String organizationName;

    private String activeFrom;

    private String deActiveFrom;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double totalPayout;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double totalSaving;

    private String createdDate;

    private String lastModifiedDate;

    private String createByUser;

    private String lastModifiedByUser;

    private String savingsType;

    private String autoRenewal;

    private List<String> availabilityOption;
}