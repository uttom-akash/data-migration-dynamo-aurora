package com.bkash.savings.models.dto.fi;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record CreateFiAccountResponseDto(
        String accountNo,
        String startDate,
        String endDate,
        String maturityDate
) implements Serializable {

    public Boolean hasAccountNo() {
        return accountNo != null && !accountNo.isEmpty();
    }
}
