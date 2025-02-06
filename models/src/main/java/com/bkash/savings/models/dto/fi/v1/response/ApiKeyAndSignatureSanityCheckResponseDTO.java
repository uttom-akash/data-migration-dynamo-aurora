package com.bkash.savings.models.dto.fi.v1.response;

import com.bkash.savings.models.common.CommonResponseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ApiKeyAndSignatureSanityCheckResponseDTO extends CommonResponseDTO {
}
