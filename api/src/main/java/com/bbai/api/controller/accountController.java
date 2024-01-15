package com.bbai.api.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
            String created = compteService.createAccount(tokenService.getTokenFromHeader(authorizationHeader),
                    request.getIdentifiant(), request.getPassword(), request.getRole());
            Map<String, String> response = new HashMap<>();
            response.put("message", created);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // Invalid token
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invalid token");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<Map<String, String>> login(@RequestBody accountModel request) {
        Map<String, String> response = new HashMap<>();
        HttpStatus status;
        try {
            Optional<accountModel> compte = compteService.getAccountByLogs(request.getIdentifiant(),
                    request.getPassword());
            response.put("identifiant", compte.get().getIdentifiant());
            response.put("role", compte.get().getRole().toString());
            response.put("token", compte.get().getToken());
            response.put("message", "OK");
            status = HttpStatus.OK;
        } catch (Exception e) {
            response.put("message", "Account not found");
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(response, status);
    }
}
