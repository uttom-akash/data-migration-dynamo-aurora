package com.bkash.savings.models.dto.fi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BkToFiPullAccountDetailsRequest extends FiBaseRequest {

    @JsonIgnore
    private String url;

    private AccountDetailsSyncerRequestBody data;

    @Builder
    public record AccountDetailsSyncerRequestBody(List<String> accountIds) {

    }
}

