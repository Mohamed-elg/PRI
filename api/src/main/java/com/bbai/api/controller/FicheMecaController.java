package com.bbai.api.controller;

import com.bbai.api.dto.FicheMecaDTO;
import com.bbai.api.service.Account.tokenValidatorService;
import com.bbai.api.service.FicheMecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api")
public class FicheMecaController {
    @Autowired
    private FicheMecaService ficheMecaService;

    @Autowired
    private tokenValidatorService tokenService;

    @PostMapping("/FicheMecanique")
    public ResponseEntity<Object> postAssemblage(@RequestBody FicheMecaDTO ficheMecaDTO, @RequestHeader("Authorization") String authorizationHeader){
        try {
            return new ResponseEntity<>(ficheMecaService.saveFicheMeca(ficheMecaDTO,tokenService.getTokenFromHeader(authorizationHeader)), HttpStatus.ACCEPTED);
        }catch(Exception e){
            if(e instanceof AccessDeniedException){
                return new ResponseEntity<>(e.toString(), HttpStatus.UNAUTHORIZED);
            }else{
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }

}
