package com.bkash.savings.models.dto.fi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FiToBkQueryWithTxIdsResponse extends FiBaseResponse{
    private FiToBkQueryWithTxIdsResult result;
}
