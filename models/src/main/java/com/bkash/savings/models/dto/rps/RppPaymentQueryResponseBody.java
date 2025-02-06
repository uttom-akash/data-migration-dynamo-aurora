package com.bkash.savings.models.dto.rps;

import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class RppPaymentQueryResponseBody extends RppResponseDTO {
    private List<PaymentStatusDTO> payments;
}
