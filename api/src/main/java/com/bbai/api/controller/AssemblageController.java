package com.bbai.api.controller;

import com.bbai.api.model.assemblage.Assemblage;
import com.bbai.api.service.account.TokenValidatorService;
import com.bbai.api.service.assemblage.AssemblageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api")
public class AssemblageController {
    @Autowired
    AssemblageService assemblageService;

    @Autowired
    private TokenValidatorService tokenService;

    @PostMapping("/assemblage")
    public ResponseEntity<Object> postAssemblage(@RequestBody Assemblage assemblage,
            @RequestHeader("Authorization") String authorizationHeader) {
        try {
            return new ResponseEntity<>(
                    assemblageService.saveAssemblage(assemblage, tokenService.getTokenFromHeader(authorizationHeader)),
                    HttpStatus.ACCEPTED);
        } catch (Exception e) {
            if (e instanceof AccessDeniedException) {
                return new ResponseEntity<>(e.toString(), HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }

    @PutMapping("/assemblage/{id}")
    public ResponseEntity<Object> putAssemblage(
            @PathVariable long id,
            @RequestBody Assemblage assemblage,
            @RequestHeader("Authorization") String authorizationHeader) {
        try {
            return new ResponseEntity<>(
                    assemblageService.updateAssemblage(id, assemblage, tokenService.getTokenFromHeader(authorizationHeader)),
                    HttpStatus.ACCEPTED);
        } catch (Exception e) {
            if (e instanceof AccessDeniedException) {
                return new ResponseEntity<>(e.toString(), HttpStatus.UNAUTHORIZED);
            } else {
                if(e instanceof EntityNotFoundException){
                    return new ResponseEntity<>(e.toString(), HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }
}
