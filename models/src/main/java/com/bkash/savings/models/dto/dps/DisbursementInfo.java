package com.bkash.savings.models.dto.dps;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Builder
// a transaction status can be added to this class for visibility
// return associated ficlosingTrxId & ficlosingTrxDate
public record DisbursementInfo(String trxId,
                               String originatorConversationId,
                               ZonedDateTime trxDateTime,
                               BigDecimal amount,
                               String fiClosingTrxId,
                               String fiClosingTrxDate) {
}
