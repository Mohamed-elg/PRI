package com.bbai.api.controller;

import com.bbai.api.dto.FicheMecaDTO;
import com.bbai.api.service.FicheMecaService;
import com.bbai.api.service.account.TokenValidatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api")
@Tag(name = "Fiche Mecanique API")
public class FicheMecaController {
    @Autowired
    private FicheMecaService ficheMecaService;

    @Autowired
    private TokenValidatorService tokenService;

    @Operation(summary = "Create a Fiche Mecanique",
            description = "Create a new Fiche Mecanique corresponding to the body ")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "202",description = "Succesfully created"),
            @ApiResponse(responseCode = "401",description = "Bad permissions"),
            @ApiResponse(responseCode = "500",description = "Internal error")
    })
    @PostMapping("/FicheMecanique")
    public ResponseEntity<Object> postFicheMeca(
            @RequestBody
            @Parameter(name = "Fiche Mecanique", description = "The Fiche Mecanique you want to create")
            FicheMecaDTO ficheMecaDTO,
            @RequestHeader("Authorization")
            @Parameter(name = "Access Token", description = "Bearer token corresponding to an existing account")
            String authorizationHeader) {
        try {
            return new ResponseEntity<>(
                    ficheMecaService.saveFicheMeca(ficheMecaDTO, tokenService.getTokenFromHeader(authorizationHeader)),
                    HttpStatus.ACCEPTED);
        } catch (Exception e) {
            if (e instanceof AccessDeniedException) {
                return new ResponseEntity<>(e.toString(), HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }

    @Operation(summary = "Get a Fiche Mecanique",
            description = "Get one or all the Fiche Mecanique")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",description = "Request accepted"),
            @ApiResponse(responseCode = "401",description = "Bad permissions"),
            @ApiResponse(responseCode = "500",description = "Internal error")
    })
    @GetMapping("/FicheMecanique")
    public ResponseEntity<Object> getFicheMeca(
            @Parameter(name = "id", description = "Id of the Fiche Mecanique (get all the fiche if not specify)")
            @RequestParam(required = false)
            Long id,
            @Parameter(name = "Access Token", description = "Bearer token corresponding to an existing account")
            @RequestHeader("Authorization")
            String authorizationHeader) {
        try {
            return new ResponseEntity<>(
                    ficheMecaService.getFicheMeca(id, tokenService.getTokenFromHeader(authorizationHeader)),
                    HttpStatus.OK);
        } catch (Exception e) {
            if (e instanceof AccessDeniedException) {
                return new ResponseEntity<>(e.toString(), HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }

}
