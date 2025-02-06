package com.bkash.savings.models.dto.gql;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record Address(String addressLine, String postalCode, String union, String thanaOrUpazilla,
                      String cityOrDistrict, String division) implements Serializable {
}