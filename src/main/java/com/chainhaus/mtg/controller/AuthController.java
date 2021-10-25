package com.chainhaus.mtg.controller;

import com.chainhaus.mtg.model.AuthToken;
import com.chainhaus.mtg.model.UserModel;
import com.chainhaus.mtg.service.AuthService;
import com.chainhaus.mtg.service.UserServiceImpl;
import com.chainhaus.mtg.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Asad Sarwar on 11/06/2020.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserModel user, HttpServletResponse response) throws AuthenticationException {
        AuthToken authToken = authService.authenticate(user);
        response.addCookie(new Cookie(AuthUtil.HEADER_STRING, authToken.getToken()));
        return ResponseEntity.ok(authToken) ;
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(){
        SecurityContextHolder.getContext().setAuthentication(null);
        return ResponseEntity.ok("Logged out");

    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserModel user){
        Boolean saved = authService.register(user);
        return ResponseEntity.ok(saved ? "User added" : "Unable to add user") ;
    }

    @GetMapping("/userDetails")
    public ResponseEntity<?> userDetails() throws AuthenticationException {
        return ResponseEntity.ok(authService.getUserDetails()) ;
    }

    @GetMapping("/encryptPasswords")
    public ResponseEntity<?> encryptPasswords() throws Exception {
        userService.encryptPasswords();
        return ResponseEntity.ok("Done") ;
    }


    @GetMapping("/validatePin/{pin}")
    public ResponseEntity<String> validatePin(@PathVariable String pin){
        return ResponseEntity.ok(pin.equals("786786") ? "Uk9MRV9VU0VSIiwiaWF0IjoxNjMzNzg5MDU5LCJleHAiOjE2MzM4MDcwNTl9" : "INVALID-PIN");
    }


}
