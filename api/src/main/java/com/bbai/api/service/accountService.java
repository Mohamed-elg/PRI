package com.bbai.api.service;

import com.bbai.api.model.accountModel;
import com.bbai.api.repository.AccountModelRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class accountService {

    @Autowired
    private AccountModelRepository accountModelRepository;

    public Optional<accountModel> getAccountByLogs(String identifiant, String password) {
        return accountModelRepository.findByIdentifiantAndPassword(identifiant, password);
    }

    public Optional<accountModel> getAccountById(long accountId) {
        return accountModelRepository.findById(accountId);
    }

    public accountModel createAccount(String identifiant, String password) {
        accountModel newAccount = new accountModel();
        newAccount.setIdentifiant(identifiant);
        newAccount.setPassword(password);
        String token = identifiant + password;
        newAccount.setToken(token);
        return accountModelRepository.save(newAccount);
    }

}
