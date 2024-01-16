package com.bbai.api.service.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class TokenValidatorService {

    @Autowired
    AccountService compteService;

    public boolean validTokenFromHeader(String authorizationHeader) {
        String incomingToken = getTokenFromHeader(authorizationHeader);
        return compteService.getAccountByToken(incomingToken).isPresent();
    }

    public String getTokenFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
            // Extract token after "Bearer "
            // TODO : DO BETTER !
        }
        return null;
    }
}
