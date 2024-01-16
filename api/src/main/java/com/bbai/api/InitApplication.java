package com.bbai.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.bbai.api.model.account.AccountModel;
import com.bbai.api.model.account.ERole;
import com.bbai.api.repository.AccountModelRepository;

@Component
public class InitApplication implements ApplicationRunner {

    @Autowired
    private AccountModelRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        try {
            AccountModel root = new AccountModel();
            root.setIdentifiant("root");
            root.setPassword("root");
            root.setRole(ERole.ADMIN);
            Integer hascode = "rootroot".hashCode();
            root.setToken(hascode.toString());
            this.accountRepository.save(root);
        } catch (Exception DataIntegrityViolationException) {
            System.out.println("Init user already exists !");
        }

    }

}
