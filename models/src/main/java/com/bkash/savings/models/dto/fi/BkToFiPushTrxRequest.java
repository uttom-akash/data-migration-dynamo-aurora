package com.bkash.savings.models.dto.fi;

import com.bkash.savings.models.postgres.transaction.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BkToFiPushTrxRequest extends XTransactionInfo {
	
	/** yyyyMMddHHmmSS formatted date string of the bKash transaction time */
	@JsonProperty("trxDate")
	private String bkashTransactionDate;
	
	@JsonProperty("bkInstallmentPlanNumber")
	private String bKashPlanNumber;
	
	private TransactionType transactionType;
	
	/** yyyyMMddHHmmss Formatted date string. <br>
	 * Optional. Only mandatory if the trnsactionType is MISSED_DEPOSIT
	 **/
	@JsonProperty("instalmentDueDate")
	private String dueDate;
	
	/** Optional. Mandatory if the transactionType is DISBURSEMENT */
	private String closingFiTransactionId;
	
	/** Optional. Mandatory if the transactionType is DISBURSEMENT. Format: yyyyMMddHHmmss */
	private String closingFiTransactionDate;
	
	/** Optional. Mandatory if the transaction type is REFUND */
	@JsonProperty("originalTrxID")
	private String originalTransactionId;
}
