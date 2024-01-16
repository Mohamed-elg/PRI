package com.bbai.api.controller;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PostMapping("/")
    public ResponseEntity<Object> createClient(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody ClientModel request) {

        try {
            ClientModel created = clientService.createClient(tokenService.getTokenFromHeader(authorizationHeader),
                    request);
            return new ResponseEntity<>(created, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            if (e instanceof AccessDeniedException) {
                response.put("message", e.getMessage().toString());
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            } else {
                response.put("message", e.getMessage().toString());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Object> getClient(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody ClientModel request, @PathVariable Long clientId) {
        try {
            ClientModel get = clientService.getClientbyId(
                    tokenService.getTokenFromHeader(authorizationHeader), clientId).get();
            return new ResponseEntity<>(get, HttpStatus.OK);
        } catch (Exception e) {

            Map<String, String> response = new HashMap<>();
            if (e instanceof AccessDeniedException) {
                response.put("message", e.getMessage().toString());
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            } else {
                response.put("message", e.getMessage().toString());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Object> updateClient(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody ClientModel request, @PathVariable Long clientId) {
        try {
            ClientModel updated = clientService.updateClientbyId(
                    tokenService.getTokenFromHeader(authorizationHeader), clientId,
                    request);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {

            Map<String, String> response = new HashMap<>();
            if (e instanceof AccessDeniedException) {
                response.put("message", e.getMessage().toString());
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            } else {
                response.put("message", e.getMessage().toString());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Object> deleClient(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody ClientModel request, @PathVariable Long clientId) {
        Map<String, String> response = new HashMap<>();

        try {
            clientService.deleteClientbyId(tokenService.getTokenFromHeader(authorizationHeader), clientId);
            response.put("message", "ok");
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
