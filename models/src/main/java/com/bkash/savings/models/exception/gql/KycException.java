package com.bkash.savings.models.exception.gql;

import com.bkash.savings.models.common.ApiResponseStatus;
import com.bkash.savings.models.exception.SavingsException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KycException extends SavingsException {

    private String walletNo;

    public KycException(ApiResponseStatus apiResponseStatus) {
        super(apiResponseStatus);
    }

    public KycException(String code, String message) {
        super(code, message);
    }

    public KycException(String code, String message, String walletNo) {
        super(code, message);
        this.walletNo = walletNo;
    }

}
