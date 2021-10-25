package com.chainhaus.mtg.model;

/**
 * Created by Asad Sarwar on 20/06/2020.
 */
public class ExceptionResponse {
    private String reason;
    private String message;

    public ExceptionResponse(String reason, String message) {
        this.reason = reason;
        this.message = message;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
