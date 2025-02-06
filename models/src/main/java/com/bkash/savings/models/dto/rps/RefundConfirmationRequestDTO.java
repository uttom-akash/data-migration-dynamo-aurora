package com.bkash.savings.models.dto.rps;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class RefundConfirmationRequestDTO {

    private String orgCode;

    private String accountNo;

    private String trxId;

    private String reverseTrxId;

    private String reverseTrxDate;
}
