package com.chainhaus.mtg.controller;

import com.chainhaus.mtg.model.UserModel;
import com.chainhaus.mtg.service.AdminService;
import com.chainhaus.mtg.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Asad Sarwar on 14/06/2020.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    RegistrationService registrationService;

    @Autowired
    AdminService adminService;

    @PostMapping("/add/user")
    public ResponseEntity<?> addUser(@RequestBody UserModel user){
        return ResponseEntity.ok(adminService.saveUser(user)) ;
    }

    @GetMapping("/list/user")
    public ResponseEntity<?> listUser() {
        return ResponseEntity.ok(adminService.getAllUsers()) ;
    }

    @PostMapping("/deactivate/user")
    public ResponseEntity<Boolean> deactivateUser(@RequestBody long id){
        return ResponseEntity.ok(adminService.deactivateUser(id));
    }

}
