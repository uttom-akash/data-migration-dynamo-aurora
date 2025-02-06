package com.bkash.savings.models.dto.product;

import java.io.Serializable;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.bkash.savings.models.postgres.account.SavingsType;
import com.bkash.savings.models.postgres.product.Term;
import com.bkash.savings.models.validators.ConditionalRequiredFieldValidator;
import com.bkash.savings.models.validators.EnumValueValidator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** TODO This class is completely copied from version 1. Improvement scope is available. */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ConditionalRequiredFieldValidator
public class ProductSchemesFilterRequest implements Serializable {

    private static final long serialVersionUID = -3816204583656741189L;

	@NotNull(message = "Filter type can not be null")
    private FilterType filterType;

    @NotEmpty(message = "Tenure can not be empty")
    private String tenure;

    @NotNull(message = "Term can not be null")
    private Term term;

    private String instalmentAmount;
    
    private String targetAmount;

    private String organizationType;

    @NotBlank(message = "Savings type can not be null")
    @EnumValueValidator(enumClass = SavingsType.class, message = "must be a valid savings type [DPS / FDR]")
    private String savingsType;

    @JsonIgnore
    private ServiceRequesterType requesterType;

    public enum FilterType {
        GOAL_BASED,
        INSTALMENT_AMOUNT_BASED
    }
    
    public boolean isGoalBasedFilter() {
    	return this.filterType == FilterType.GOAL_BASED;
    }
    
    public boolean isTargetAmountValid() {
    	if (filterType == FilterType.GOAL_BASED && StringUtils.isBlank(targetAmount)) return false;
    	return targetAmount.trim().matches("\\d+(\\.\\d+)?");
    }
    
    public boolean isTenureValid() {
    	System.out.println(tenure);
        Pattern pattern = Pattern.compile("\\d+");
        return StringUtils.isNoneBlank(tenure) && pattern.matcher(tenure.trim()).matches();
    }
    
    public boolean isInstalmentAmountValid() {
        Pattern pattern = Pattern.compile("^\\d+(\\.\\d+)?$");
        return StringUtils.isNoneBlank(instalmentAmount) && pattern.matcher(instalmentAmount.trim()).matches();
    }
}
