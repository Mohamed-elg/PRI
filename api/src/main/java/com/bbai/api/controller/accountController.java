package com.bbai.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.bbai.api.model.accountModel;
import com.bbai.api.service.accountService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class accountController {

    @Autowired
    accountService compteService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> CreateAccount(@RequestBody accountModel request) {
        compteService.createAccount(request.getIdentifiant(), request.getPassword());
        Map<String, String> response = new HashMap<>();
        response.put("message", "Account created successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/token")
    public ResponseEntity<Map<String, String>> login(@RequestBody accountModel request) {
        Map<String, String> response = new HashMap<>();
        response.put("token",
                compteService.getAccountByLogs(request.getIdentifiant(), request.getPassword()).get().getToken());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
