package com.bbai.api.service;

import com.bbai.api.model.ERole;
import com.bbai.api.model.accountModel;
import com.bbai.api.repository.AccountModelRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public Optional<accountModel> getAccountByToken(String token) {
        return accountModelRepository.findByToken(token);
    }

    public String createAccount(String Usertoken, String identifiant, String password, ERole role) {
        if (this.getAccountByToken(Usertoken).get().getRole() == ERole.ADMIN) {
            accountModel newAccount = new accountModel();
            newAccount.setIdentifiant(identifiant);
            newAccount.setPassword(password);
            newAccount.setRole(role);
            Integer stringHash = (identifiant + password + role.toString()).hashCode();
            newAccount.setToken(stringHash.toString());
            accountModelRepository.save(newAccount);
            return "Account created successfully";
        }
        return "You are not ADMIN";
    }

}
