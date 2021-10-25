package com.chainhaus.mtg.exception.exception;

/**
 * Created by Asad Sarwar on 20/06/2020.
 */
public class FieldValueException extends RuntimeException {
    public static final String DUPLICATE_VALUE = "Duplicate Value";
    public static final String REQUIRED_VALUE = "Required Value";
    public static final String INVALID_VALUE = "Invalid Value";

    private String reason;
    private String fieldName;
    public FieldValueException (String reason, String fieldName){
        super(reason);
        this.reason = reason;
        this.fieldName = fieldName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
