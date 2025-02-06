package com.bkash.savings.models.dto.fi.v1.request;


import com.bkash.savings.models.dto.fi.v1.TestDataDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class ApiKeyAndSignatureSanityCheckRequestDTO extends RequestBody {

    @Valid
    private TestDataDTO data;

    @NotBlank(message = "Organization code cannot be empty")
    private String orgCode;

    @NotBlank(message = "Signature cannot be empty")
    private String signature;
}
