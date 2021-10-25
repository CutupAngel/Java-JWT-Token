package com.chainhaus.mtg.exception.exception;

/**
 * Created by Jamali on 9/9/2021.
 */
public class IPFSException extends RuntimeException {
    private String reason;
    public IPFSException(String reason){
        super(reason);
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
