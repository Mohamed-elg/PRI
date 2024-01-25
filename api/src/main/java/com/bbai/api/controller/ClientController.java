package com.bbai.api.controller;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Client API")
public class ClientController {

    @Autowired
    ClientService clientService;

    @Autowired
    private TokenValidatorService tokenService;

    @Operation(summary = "Create a client",
            description = "Create a new client corresponding to the body ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Succesfully created"),
            @ApiResponse(responseCode = "401", description = "Bad permissions"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @PostMapping("/")
    public ResponseEntity<Object> createClient(
            @RequestHeader("Authorization")
            @Parameter(name = "Access Token", description = "Bearer token corresponding to an existing account")
            String authorizationHeader,
            @RequestBody
            @Parameter(name = "Client", description = "The Client you want to create")
            ClientModel request) {

        try {
            ClientModel created = clientService.createClient(tokenService.getTokenFromHeader(authorizationHeader),
                    request);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            if (e instanceof AccessDeniedException) {
                response.put("message", e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            } else {
                response.put("message", e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Operation(summary = "Get a client",
            description = "Get a client by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Succesfully created"),
            @ApiResponse(responseCode = "401", description = "Bad permissions"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @GetMapping("/{clientId}")
    public ResponseEntity<Object> getClient(
            @RequestHeader("Authorization")
            @Parameter(name = "Access Token", description = "Bearer token corresponding to an existing account")
            String authorizationHeader,
            @Parameter(name = "ClientID", description = "The id of the client ")
            @PathVariable
            Long clientId) {
        try {
            ClientModel get = clientService.getClientbyId(
                    tokenService.getTokenFromHeader(authorizationHeader), clientId).get();
            return new ResponseEntity<>(get, HttpStatus.OK);
        } catch (Exception e) {

            Map<String, String> response = new HashMap<>();
            if (e instanceof AccessDeniedException) {
                response.put("message", e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            } else {
                response.put("message", e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }

    @Operation(summary = "Modify a client",
            description = "Modify a client by proividing its id and the new object.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Succesfully created"),
            @ApiResponse(responseCode = "401", description = "Bad permissions"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @PutMapping("/{clientId}")
    public ResponseEntity<Object> updateClient(
            @RequestHeader("Authorization")
            @Parameter(name = "Access Token", description = "Bearer token corresponding to an existing account")
            String authorizationHeader,
            @RequestBody
            @Parameter(name = "Client", description = "The new version of the client")
            ClientModel request,
            @Parameter(name = "ClientID", description = "The id of the client ")
            @PathVariable
            Long clientId) {
        try {
            ClientModel updated = clientService.updateClientbyId(
                    tokenService.getTokenFromHeader(authorizationHeader), clientId,
                    request);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {

            Map<String, String> response = new HashMap<>();
            if (e instanceof AccessDeniedException) {
                response.put("message", e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            } else {
                response.put("message", e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }


    @Operation(summary = "Delete a client",
            description = "Delete a client by proividing its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Succesfully created"),
            @ApiResponse(responseCode = "401", description = "Bad permissions"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @DeleteMapping("/{clientId}")
    public ResponseEntity<Object> deleClient(
            @RequestHeader("Authorization")
            @Parameter(name = "Access Token", description = "Bearer token corresponding to an existing account")
            String authorizationHeader,
            @Parameter(name = "ClientID", description = "The id of the client ")
            @PathVariable
            Long clientId) {
        Map<String, String> response = new HashMap<>();

        try {
            clientService.deleteClientbyId(tokenService.getTokenFromHeader(authorizationHeader), clientId);
            response.put("message", "ok");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {

            if (e instanceof AccessDeniedException) {
                response.put("message", e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            } else {
                response.put("message", e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }

}
