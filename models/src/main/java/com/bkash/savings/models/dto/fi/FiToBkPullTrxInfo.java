package com.bkash.savings.models.dto.fi;

import com.bkash.savings.models.postgres.transaction.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FiToBkPullTrxInfo  extends XTransactionInfo {

	private TransactionType transactionType;
	
	@JsonProperty("trxDate")
	private String bKashTransactionDate;
	
	/** yyyyMMddHHmms - Optional - Mandatory if it is Missed Payment transaction */
	private String instalmentDueDate;
	
	private String bkInstallmentPlanNumber;
	
	/** Optional - Mandatory if it is Refund typed transaction */
	private String originalTrxId;
}
