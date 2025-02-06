package com.bkash.savings.models.dto.rps;

import com.bkash.savings.models.postgres.organization.OrganizationType;
import com.bkash.savings.models.postgres.product.Term;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RpsCreateRequestDto {

    private String savingsId;

    private String walletId;

    private String fiAccountId;

    private LocalDate cycleStartDate;

    private LocalDate cycleEndDate;

    private LocalDate activationDate;

    private Term term;

    private BigDecimal amount;

    private String organizationCode;

    private String merchantShortCode;

    private OrganizationType organizationType;
}
