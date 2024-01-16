package com.bbai.api.service.assemblage;

import com.bbai.api.model.assemblage.Assemblage;
import com.bbai.api.repository.assemblage.AssemblageRepository;
import com.bbai.api.service.account.AccountService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

@Service
public class AssemblageService {
    @Autowired
    AssemblageRepository assemblageRepository;

    @Autowired
    AccountService accountService;

    public Assemblage saveAssemblage(Assemblage assemblage, String token) throws AccessDeniedException {

        if (accountService.getAccountByToken(token).isEmpty()) {
            throw new AccessDeniedException("Access is forbidden: invalid token");
        }

        return assemblageRepository.save(assemblage);
    }

    public Assemblage updateAssemblage(Long id, Assemblage assemblage, String token) throws AccessDeniedException, EntityNotFoundException{
        if (accountService.getAccountByToken(token).isEmpty()) {
            throw new AccessDeniedException("Access is forbidden: invalid token");
        }

        Optional<Assemblage> assemblageToUpdate = assemblageRepository.findById(id);
        if(assemblageToUpdate.isEmpty()){
            throw new EntityNotFoundException("The provided id doesn't match any Assemblage");
        }

        assemblage.setId(assemblageToUpdate.get().getId());
        return assemblageRepository.save(assemblage);
    }

}
