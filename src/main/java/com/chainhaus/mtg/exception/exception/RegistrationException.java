package com.chainhaus.mtg.exception.exception;

/**
 * Created by Asad Sarwar on 14/06/2020.
 */
public class RegistrationException extends RuntimeException {

    public static final String DUPLICATE_ENTRY = "DUPLICATE ENTRY";

    public String getCode(){
        return "";
    }

    public RegistrationException(String message, Throwable cause){
        super(message, cause);
    }
    public RegistrationException(Throwable cause){
        super(cause);
    }
    public RegistrationException(String message){
        super(message);
    }
    public RegistrationException(){
        super();
    }
}
