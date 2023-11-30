package com.bbai.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.bbai.api.model.accountModel;
import com.bbai.api.service.accountService;
import com.bbai.api.service.tokenValidatorService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class accountController {

    @Autowired
    accountService compteService;

    @Autowired
    private tokenValidatorService tokenService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createAccount(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody accountModel request) {

        // Validate the token
        if (tokenService.validTokenFromHeader(authorizationHeader)) {
            compteService.createAccount(request.getIdentifiant(), request.getPassword());
            Map<String, String> response = new HashMap<>();
            response.put("message", "Account created successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // Invalid token
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invalid token");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/token")
    public ResponseEntity<Map<String, String>> login(@RequestBody accountModel request) {
        Map<String, String> response = new HashMap<>();
        response.put("token",
                compteService.getAccountByLogs(request.getIdentifiant(), request.getPassword()).get().getToken());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
