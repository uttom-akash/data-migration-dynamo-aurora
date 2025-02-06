package com.bkash.savings.models.dto.fi;

import com.bkash.savings.models.postgres.product.Term;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

/**
 * // TODO: review for removal
 * {@link com.bkash.savings.models.postgres.product.dto.ProductDto}
 * {@link com.bkash.savings.models.dto.product.ProductDTO}
 *
 * The above DTO classes are similar to this one. We may consider removing them if deemed necessary and feasible. 
 * <h3>However, this may not always be possible.</h3>
 * <p>
 *     For instance, if {@link com.bkash.savings.models.postgres.product.dto.ProductDto} is used universally, it could introduce 
 *     additional complexity. While these DTO classes contain the same information, their types occasionally differ, 
 *     which might cause challenges.
 * </p>
 * <p>
 *     Therefore, we are keeping them as is for now and will revisit it after further discussion.
 * </p>
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTOForFiMaturityDetails implements Serializable {

    @NotBlank(message = "Tenure cannot be empty")
    private String tenure;

    @NotNull(message = "Term must not be empty")
    private Term term;

    @NotBlank(message = "Amount cannot be empty")
    private String amount;

    @NotBlank(message = "Product ID cannot be empty")
    private String productId;
}