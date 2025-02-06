package com.bkash.savings.models.dto.rps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RppResponseDTO implements Serializable {

    private String updateTime;

    private String errorCode;

    private String errorMessageEn;

    private String errorMessageBn;

    private String resultType;

    private String resultCode;

    private String resultDesc;

    private String originatorConversationId;

    private String conversationId;

    private boolean requestTimeOut;
}
