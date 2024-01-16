package com.bbai.api.controller;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bbai.api.model.account.AccountModel;
import com.bbai.api.service.account.AccountService;
import com.bbai.api.service.account.TokenValidatorService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    AccountService compteService;

    @Autowired
    private TokenValidatorService tokenService;

    @PostMapping("/create")
    public ResponseEntity<Object> createAccount(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody AccountModel request) {

        Map<String, String> response = new HashMap<>();
        try {
            String created = compteService.createAccount(tokenService.getTokenFromHeader(authorizationHeader),
                    request.getIdentifiant(), request.getPassword(), request.getRole());
            response.put("message", created);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            if (e instanceof DataIntegrityViolationException) {
                response.put("message", e.getMessage().toString());
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            } else {
                if (e instanceof AccessDeniedException) {
                    response.put("message", e.getMessage().toString());
                    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
                } else {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<Map<String, String>> login(@RequestBody AccountModel request) {
        Map<String, String> response = new HashMap<>();
        try {
            Optional<AccountModel> compte = compteService.getAccountByLogs(request.getIdentifiant(),
                    request.getPassword());
            response.put("identifiant", compte.get().getIdentifiant());
            response.put("role", compte.get().getRole().toString());
            response.put("token", compte.get().getToken());
            response.put("message", "OK");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {

            if (e instanceof AccessDeniedException) {
                response.put("message", e.getMessage().toString());
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            } else {
                response.put("message", e.getMessage().toString());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }

    }
}
