package com.bbai.api.controller;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="Account API")
public class AccountController {

    @Autowired
    AccountService compteService;

    @Autowired
    private TokenValidatorService tokenService;

    @Operation(summary = "Create an account",description = "Create the account corresponding to the body " +
            "(You need to be an admin to do this operation)")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "202",description = "Succesfully created"),
            @ApiResponse(responseCode = "409",description = "The account already exists"),
            @ApiResponse(responseCode = "401",description = "Bad permissions"),
            @ApiResponse(responseCode = "500",description = "Internal error")
    })
    @PostMapping("/create")
    public ResponseEntity<Object> createAccount(
            @RequestHeader("Authorization")
                @Parameter(name = "Access Token", description = "Bearer token corresponding to an admin account")
                String authorizationHeader,
            @RequestBody
                @Parameter(name = "Account", description = "The account you want to create")
                AccountModel request)
    {

        Map<String, String> response = new HashMap<>();
        try {
            String created = compteService.createAccount(tokenService.getTokenFromHeader(authorizationHeader),
                    request.getIdentifiant(), request.getPassword(), request.getRole());
            response.put("message", created);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            if (e instanceof DataIntegrityViolationException) {
                response.put("message", e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            } else {
                if (e instanceof AccessDeniedException) {
                    response.put("message", e.getMessage());
                    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
                } else {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
    }

    @Operation(summary = "Authentification",description = "Provide the token of your account")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",description = "Request accepted"),
            @ApiResponse(responseCode = "401",description = "Invalid login"),
            @ApiResponse(responseCode = "500",description = "Internal error")
    })
    @PostMapping("/auth")
    public ResponseEntity<Map<String, String>> login(
            @RequestBody
            @Parameter(name = "Login informations", description = "identifiant and password")
            AccountModel request)
    {
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

            if (e instanceof NoSuchElementException) {
                response.put("message", "Invalid login");
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            } else {
                response.put("message", e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }

    }
}
