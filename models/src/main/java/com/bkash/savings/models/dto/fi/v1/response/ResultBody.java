package com.bkash.savings.models.dto.fi.v1.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResultBody implements Serializable {
    private String resultCode;
    private String resultStatus;
    private String resultMessage;
    private Object data;
    private String orgCode;
}
