package com.bkash.savings.models.dto.fi;

public record MaturityDetailsResponseDTO(
        Double interestRate,
        String productType,
        String maturityDate,
        Double totalInterestEarning,
        Double totalMaturedAmount,
        String nextInstallmentDueDate,
        String autoDeductionTime,
        Double totalDepositAmount
) {
}
