package com.bbai.api.service.account;

import com.bbai.api.model.account.ERole;
import com.bbai.api.model.account.AccountModel;
import com.bbai.api.repository.AccountModelRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class AccountService {

    @Autowired
    private AccountModelRepository accountModelRepository;

    public Optional<AccountModel> getAccountByLogs(String identifiant, String password) {
        return accountModelRepository.findByIdentifiantAndPassword(identifiant, password);
    }

    public Optional<AccountModel> getAccountById(long accountId) {
        return accountModelRepository.findById(accountId);
    }

    public Optional<AccountModel> getAccountByToken(String token) {
        return accountModelRepository.findByToken(token);
    }

    public String createAccount(String Usertoken, String identifiant, String password, ERole role)
            throws AccessDeniedException {
        if (this.getAccountByToken(Usertoken).isPresent()) {
            if (this.getAccountByToken(Usertoken).get().getRole() == ERole.ADMIN) {
                AccountModel newAccount = new AccountModel();
                newAccount.setIdentifiant(identifiant);
                newAccount.setPassword(password);
                newAccount.setRole(role);
                Integer stringHash = (identifiant + password + role.toString()).hashCode();
                newAccount.setToken(stringHash.toString());
                accountModelRepository.save(newAccount);
                return "Account created successfully";
            }
            throw new AccessDeniedException("Access is forbidden: you are not ADMIN");
        } else {
            throw new AccessDeniedException("Access is forbidden: invalid token");
        }

    }

}
