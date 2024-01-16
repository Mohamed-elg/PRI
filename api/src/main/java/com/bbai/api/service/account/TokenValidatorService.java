package com.bbai.api.service.account;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            Pattern pattern = Pattern.compile("Bearer (.*)");
            Matcher matcher = pattern.matcher(authorizationHeader);

            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        return null;
    }

}
