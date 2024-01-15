package com.bbai.api.service.assemblage;

import com.bbai.api.model.assemblage.Assemblage;
import com.bbai.api.repository.assemblage.AssemblageRepository;
import com.bbai.api.service.Account.accountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
public class AssemblageService {
    @Autowired
    AssemblageRepository assemblageRepository;

    @Autowired
    accountService accountService;

    public Assemblage saveAssemblage(Assemblage assemblage, String token) throws AccessDeniedException {

        if (accountService.getAccountByToken(token).isEmpty()) {
            throw new AccessDeniedException("Access is forbidden: invalid token");
        }

        return assemblageRepository.save(assemblage);
    }

}
