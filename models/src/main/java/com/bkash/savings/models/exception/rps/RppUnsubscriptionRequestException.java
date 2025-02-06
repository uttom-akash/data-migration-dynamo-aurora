package com.bkash.savings.models.exception.rps;

import com.bkash.savings.models.exception.SavingsException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RppUnsubscriptionRequestException extends SavingsException {

    private String walletNo;
    private String cancelRequestedBy;

    public RppUnsubscriptionRequestException(String code, String message) {
        super(code, message);
    }

    public RppUnsubscriptionRequestException(String code, String message, String walletNo, String cancelRequestedBy) {
        super(code, message);
        this.walletNo = walletNo;
        this.cancelRequestedBy = cancelRequestedBy;
    }
}
