package com.bkash.savings.models.dto.fi.v1;

import com.bkash.savings.models.dto.fi.v1.request.RequestBody;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


/**
 * TODO: Remove this, if not needed
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TestDataDTO extends RequestBody {

    @NotBlank(message = "Request id cannot be empty")
    private String requestId;

    @NotBlank(message = "Account no cannot be empty")
    private String accountNo;

}
