package com.bbai.api.service;

import com.bbai.api.dto.FicheMecaDTO;
import com.bbai.api.model.Account.accountModel;
import com.bbai.api.model.FicheMeca;
import com.bbai.api.repository.FicheMecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class FicheMecaService {
    @Autowired
    FicheMecaRepository ficheMecaRepository;

    @Autowired
    com.bbai.api.service.Account.accountService accountService;

    public FicheMeca saveFicheMeca(FicheMecaDTO ficheMecaDTO, String token) throws AccessDeniedException {
        Optional<accountModel> account = accountService.getAccountByToken(token);
        if (account.isEmpty()) {
            throw new AccessDeniedException("Access is forbidden: invalid token");
        }

        FicheMeca ficheMeca = new FicheMeca();
        ficheMeca.setClient(ficheMecaDTO.getClient());
        ficheMeca.setAssemblage(ficheMecaDTO.getAssemblage());
        ficheMeca.setDateCreation(LocalDate.now());
        ficheMeca.setModifiedBy(account.get().getIdentifiant());
        ficheMeca.setLastModification(LocalDate.now());
        return ficheMecaRepository.save(ficheMeca);
    }
}
