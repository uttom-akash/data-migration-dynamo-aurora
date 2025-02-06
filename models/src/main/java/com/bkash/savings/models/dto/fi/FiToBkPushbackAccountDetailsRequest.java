package com.bkash.savings.models.dto.fi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FiToBkPushbackAccountDetailsRequest extends FiBaseRequest {

    private FiToBkAccountDetailsSyncerData data;


    @Builder
    public record FiToBkAccountDetailsSyncerData(
            List<FiToBkAccountDetailsBriefInfo> accountsDetailsList
    ) {
    }

    @Builder
    public record FiToBkAccountDetailsBriefInfo(
            String accountId,
            Double currentAmount,
            Double currentDepositedAmount,
            Double currentInterestAmount
    ) {

    }
}
