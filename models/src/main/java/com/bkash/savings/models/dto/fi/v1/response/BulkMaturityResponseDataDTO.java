package com.bkash.savings.models.dto.fi.v1.response;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BulkMaturityResponseDataDTO {

    private String accountNo;

    private String maturityDate;
}
