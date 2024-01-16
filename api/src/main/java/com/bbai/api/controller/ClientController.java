package com.bbai.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bbai.api.model.client.ClientModel;
import com.bbai.api.service.account.TokenValidatorService;
import com.bbai.api.service.client.ClientService;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @Autowired
    private TokenValidatorService tokenService;

    @PostMapping("/create")
    public ResponseEntity<Object> createClient(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody ClientModel request) {
        // Validate the token
        try {
            if (tokenService.validTokenFromHeader(authorizationHeader)) {
                ClientModel created = clientService.createClient(tokenService.getTokenFromHeader(authorizationHeader),
                        request);
                return new ResponseEntity<>(created, HttpStatus.OK);
            } else {
                // Invalid token
                Map<String, String> response = new HashMap<>();
                response.put("message", "Invalid token");
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
