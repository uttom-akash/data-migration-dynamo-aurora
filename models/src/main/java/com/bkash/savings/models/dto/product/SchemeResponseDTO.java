package com.bkash.savings.models.dto.product;

import com.bkash.savings.models.postgres.product.Term;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SchemeResponseDTO implements Serializable {

    private String productId;
    private String tenure;
    private Term term;
    private String amount;
}
