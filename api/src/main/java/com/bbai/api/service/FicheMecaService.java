package com.bbai.api.service;

import com.bbai.api.dto.FicheMecaDTO;
import com.bbai.api.model.FicheMeca;
import com.bbai.api.model.Option;
import com.bbai.api.model.account.AccountModel;
import com.bbai.api.repository.FicheMecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FicheMecaService {
    @Autowired
    FicheMecaRepository ficheMecaRepository;

    @Autowired
    com.bbai.api.service.account.AccountService accountService;

    @Autowired
    OptionService optionService;


    @Transactional
    public FicheMeca saveFicheMeca(FicheMecaDTO ficheMecaDTO, String token) throws AccessDeniedException {
        Optional<AccountModel> account = accountService.getAccountByToken(token);
        if (account.isEmpty()) {
            throw new AccessDeniedException("Access is forbidden: invalid token");
        }

        FicheMeca ficheMeca = new FicheMeca();
        ficheMeca.setNumeroDossier(ficheMecaDTO.getNumeroDossier());
        ficheMeca.setClient(ficheMecaDTO.getClient());
        ficheMeca.setAssemblage(ficheMecaDTO.getAssemblage());

        List<Option> options = optionService.optionFromDTO(ficheMecaDTO.getOptions());
        ficheMeca.setOptions(options);

        ficheMeca.setDateCreation(LocalDate.now());
        ficheMeca.setModifiedBy(account.get().getIdentifiant());
        ficheMeca.setLastModification(LocalDate.now());
        return ficheMecaRepository.save(ficheMeca);
    }

    public List<FicheMeca> getFicheMeca(Long id, String token) throws AccessDeniedException {
        Optional<AccountModel> account = accountService.getAccountByToken(token);
        if (account.isEmpty()) {
            throw new AccessDeniedException("Access is forbidden: invalid token");
        }

        List<FicheMeca> ficheMecas = new ArrayList<>();
        if (id != null) {
            Optional<FicheMeca> ficheMeca = ficheMecaRepository.findById(id);
            ficheMeca.ifPresent(ficheMecas::add);
        } else {
            ficheMecas.addAll(ficheMecaRepository.findAll());
        }
        return ficheMecas;
    }
}
