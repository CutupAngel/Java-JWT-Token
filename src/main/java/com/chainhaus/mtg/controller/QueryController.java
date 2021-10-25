package com.chainhaus.mtg.controller;

import com.chainhaus.mtg.entity.Minted;
import com.chainhaus.mtg.model.SelectModel;
import com.chainhaus.mtg.repository.MintedRepository;
import com.chainhaus.mtg.service.AdminService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Asad Sarwar on 14/06/2020.
 */
@RestController
@RequestMapping("/query")
public class QueryController {

    @Autowired
    AdminService adminService;

    @Autowired
    MintedRepository mintedRepository;

    @RequestMapping(value = "/select/roleTypes")
    ResponseEntity<List<SelectModel>> getUserRoles() { return ResponseEntity.ok(adminService.getUserRoles()); }


    @GetMapping("/minted")
    ResponseEntity<List<Minted>> getMinted() {
        return ResponseEntity.ok(mintedRepository.findAll());
    }

    @GetMapping("/minted/{mintedId}")
    ResponseEntity<Minted> getMinted(@PathVariable String mintedId) {
        return ResponseEntity.ok(mintedRepository.findById(Long.parseLong(mintedId)).get());
    }

}
