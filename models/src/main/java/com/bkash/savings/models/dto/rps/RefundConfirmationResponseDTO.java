package com.bkash.savings.models.dto.rps;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RefundConfirmationResponseDTO {

    private String resultCode;

    private String resultStatus;

    private String resultMessage;
}
