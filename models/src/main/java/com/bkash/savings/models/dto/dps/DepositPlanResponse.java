package com.bkash.savings.models.dto.dps;

import com.bkash.savings.models.common.ResponseStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class DepositPlanResponse {

    private static final long serialVersionUID = 4361646398761190094L;
    private String autoDeductionTime;
    @Builder.Default
    private List<InstallmentPlanInfo> installmentPlan = new ArrayList<>();

    // flag new feature disable option for Missed alert, missed payment option, cycle date change.
    private boolean newFeatureDisable;

    // missed payment time limit expired
    private boolean missedPayTimeLimitExpired;

    /**
     * Format : yyyyMMddhhmmss , e.g: 20240315000000
     */
    private String maturedDate;

    private ResponseStatus responseStatus;
}
