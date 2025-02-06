package com.bkash.savings.models.properties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Gql {
    @NotBlank
    String accountUrl;
    @NotBlank
    String transactionUrl;
    @NotNull
    @Positive
    Integer timeout;
}
