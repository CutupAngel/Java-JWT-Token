package com.chainhaus.mtg.configuration;

import com.chainhaus.mtg.util.Constants;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Asad Sarwar on 17/06/2020.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

//        if(request.getServletPath().startsWith("/api")){
            response.sendError(Constants.CUSTOM_UN_AUTHENTICATED_CODE, Constants.CUSTOM_UN_AUTHENTICATED_MESSAGE);
//        }else{
//            response.sendRedirect("/login");
//        }
    }
}