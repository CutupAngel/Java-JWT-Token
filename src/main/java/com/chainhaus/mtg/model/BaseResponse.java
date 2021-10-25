package com.chainhaus.mtg.model;


/**
 * Created by Asad Sarwar on 27/06/2020.
 */
public class BaseResponse {

    private Boolean success;

    private String message;

    public BaseResponse(String message) {
        this.message = message;
    }

    public BaseResponse(Boolean success, String message) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
