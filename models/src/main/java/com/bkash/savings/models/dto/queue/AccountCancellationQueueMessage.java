package com.bkash.savings.models.dto.queue;

import com.bkash.savings.models.postgres.account.SavingsAccountEntity;
import com.bkash.savings.models.postgres.organization.OrganizationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AccountCancellationQueueMessage {
    private CancelAccountQueueState cancelProcessState;
    private String savingsId;
    private String walletId;
    private String clearableAmount;
    private boolean isFIRequest;
    private Integer retryCount;
    private String traceId;
    private SavingsAccountEntity savingsAccountEntity;
    private OrganizationEntity organizationEntity;
}