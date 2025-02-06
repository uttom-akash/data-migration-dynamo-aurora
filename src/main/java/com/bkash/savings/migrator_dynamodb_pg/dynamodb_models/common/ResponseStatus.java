//package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.common;
//
//import com.bkash.savings.models.common.ViolationError;
//import com.bkash.savings.models.common.util.ApiResponseStatus;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.experimental.SuperBuilder;
//
//import java.io.Serializable;
//import java.util.List;
//
//@Data
//@SuperBuilder
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class ResponseStatus implements Serializable {
//
//    @Builder.Default
//    private boolean success = true;
//
//    @Builder.Default
//    private String code = ApiResponseStatus.SUCCESSFUL.code();
//
//    @Builder.Default
//    private String message = ApiResponseStatus.SUCCESSFUL.message();
//
//    private String messageBn;
//
//    private List<ViolationError> violationErrors;
//
//    private String responseTime;
//
//    private String traceId;
//
//    public boolean isSuccess()  {
//    	return this.success;
//    }
//}
