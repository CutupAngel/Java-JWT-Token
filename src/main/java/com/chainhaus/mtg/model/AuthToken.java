package com.chainhaus.mtg.model;

/**
 * Created by Asad Sarwar on 17/06/2020.
 */
public class AuthToken {
    private String token;
    private Long userId;

    public AuthToken(){

    }

    public AuthToken(String token, Long userId){
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
