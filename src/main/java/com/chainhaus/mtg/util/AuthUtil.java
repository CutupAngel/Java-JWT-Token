package com.chainhaus.mtg.util;

import com.chainhaus.mtg.entity.User;
import com.chainhaus.mtg.model.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.Cookie;

/**
 * Created by Asad Sarwar on 11/06/2020.
 */
public class AuthUtil {
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
    public static final String SIGNING_KEY = "very_very%%%SECURE";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";
    public static final String COOKIE_NAME = "MTG_USER_TOKEN";

    public static String getLoggedInUserName(){
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

    public static Long getLoggedInUserId(){
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUserId();
    }

    public static User getLoggedInUser() {
        User loginUser = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            loginUser = (User) authentication.getPrincipal();
        }

        return loginUser;
    }

    public static String getTokenCookie(Cookie[] cookies){
        for(Cookie cookie : cookies){
            if(cookie.getName().equalsIgnoreCase(COOKIE_NAME)){
                return cookie.getValue();
            }
        }

        return null;
    }

}
