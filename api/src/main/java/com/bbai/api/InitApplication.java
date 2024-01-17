package com.bbai.api;

import com.bbai.api.model.Option;
import com.bbai.api.repository.OptionRepository;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.bbai.api.model.account.AccountModel;
import com.bbai.api.model.account.ERole;
import com.bbai.api.repository.AccountModelRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitApplication implements ApplicationRunner {

    @Autowired
    private Environment environment;

    @Autowired
    private AccountModelRepository accountRepository;

    @Autowired
    private OptionRepository optionRepository;

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
            System.out.println("           ------         ");
            System.out.println("Init user already exists !");
            System.out.println("           ------         ");
        }

        try {
            List<Option> options = new ArrayList<>();
            options.add(new Option("Codeur"));
            options.add(new Option("Dynamo"));
            options.add(new Option("VF"));
            options.add(new Option("Frein"));
            options.add(new Option("Sondes"));
            options.add(new Option("Accouplement"));
            options.add(new Option("Poulie"));
            options.add(new Option("Pignon"));

            optionRepository.saveAll(options);
        } catch (Exception DataIntegrityViolationException) {
            System.out.println("           --------         ");
            System.out.println("Base options already exist !");
            System.out.println("           --------         ");
        }

        try {
            String databaseUrl = environment.getProperty("spring.datasource.url");
            System.out.println("     -------     ");
            System.out.println("Using DB :" + databaseUrl);
            System.out.println("     -------     ");
        } catch (Exception e) {
            System.out.println("     -------     ");
            System.out.println("Error fetching DB variable");
            System.out.println("     -------     ");
        }

    }

}
