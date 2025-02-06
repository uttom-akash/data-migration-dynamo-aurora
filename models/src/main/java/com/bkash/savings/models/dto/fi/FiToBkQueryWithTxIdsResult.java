package com.bkash.savings.models.dto.fi;

import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiToBkQueryWithTxIdsResult extends FiBaseResult{
    private List<FiToBkQueryTrxInfo>  data;

}
