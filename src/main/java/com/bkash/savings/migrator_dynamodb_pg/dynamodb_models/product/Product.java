package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.product;

import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.product.dto.Term;
import com.bkash.savings.models.postgres.account.SavingsType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product implements Serializable {

    private String productId;

    private String tenure;

    private Term term;

    private String amount;

    private SavingsType savingsType = SavingsType.DPS;

    private String autoRenewal;

    private String autoRenewalMode;

    @JsonIgnore
    public ProductEntity toEntity() {
        ProductEntity product = new ProductEntity();

        product.setTenure(tenure);
        product.setTerm(term.toString());
        product.setAmount(amount);
        product.setProductId(productId);
        product.setAutoRenewal(autoRenewal);
        product.setAutoRenewalMode(autoRenewalMode);

        return product;
    }
}
