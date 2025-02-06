package com.bkash.savings.models.dto.fi;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;


@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class FiToBkPushAccountDetailsRequest extends FiBaseRequest {

    private AccountDetailsSyncerResponseBody data;

    private record AccountDetailsSyncerResponseBody(
            List<BriefAccountDetailsFromFi> accountsDetailsList) {
    }

    private record BriefAccountDetailsFromFi(String accountId,
                                             BigDecimal currentAmount,
                                             BigDecimal currentDepositedAmount,
                                             BigDecimal currentInterestAmount) {
    }
}
