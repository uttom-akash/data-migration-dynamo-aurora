package com.bkash.savings.models.dto.fi.v1.response;

/**
 * Copied from the previous codebase.
 * 
 * TODO: remove it if not needed.
 */
public enum FiResponseStatus {

    BULK_SUCCESS("Success", "Success"),
    BULK_FAILED("Failed", "Failed"),
    BULK_PARTIAL_SUCCESS("PartialSuccess", "PartialSuccess"),
    SUCCESSFUL("S", "Successful"),
    FAILED("F", "Failed");

    private final String status;
    private final String message;

    FiResponseStatus(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
