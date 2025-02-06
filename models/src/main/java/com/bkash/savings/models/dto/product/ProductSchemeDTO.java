package com.bkash.savings.models.dto.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/** TODO This class is completely copied from v1. Improvement scope is available. */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProductSchemeDTO implements Serializable, Comparable<ProductSchemeDTO> {

    private static final long serialVersionUID = 4445222264847103378L;

	// organization code. Example: IDLC
    private String organizationCode;

    // organization type: REGULAR/ISLAMIC
    private String organizationType;

    // organization Name: BRAC Bank Limited
    private String organizationName;

    // Logo url.
    private String organizationLogo;

    // Terms and conditions url.
    private String termsAndConditionsUrl;
    
    private String termsAndConditionsUrlBn;

    // product details
    private ProductDTO product;

    // product Type [SHORT/LONG]
    private String productType;

    // interest rate. Example : 7.5
    private Double interest;

    // Total payout: Example: 12456.0
    private Double totalPayout;

    // Total saving amount: 250.0
    private Double totalSaving;

    // popular tag
    private boolean popular;

    private String productId;

    // flag new feature disable option for Missed alert, missed payment option, cycle date change.
    private boolean newFeatureDisable;

    @Builder.Default
    private List<SavingsSchemeTag> tags = new ArrayList<>();

    @Override
	public int compareTo(ProductSchemeDTO arg0) {
		if (Objects.isNull(arg0)) return 1;
	    return Double.compare(this.totalPayout, arg0.totalPayout);
	}
    
    public ProductSchemeDTO addTag(SavingsSchemeTag tag) {
    	if (tags == null) tags = new ArrayList<>();
    	tags.add(tag);
    	return this;
    }
}
