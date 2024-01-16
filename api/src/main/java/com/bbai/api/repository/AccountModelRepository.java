package com.bbai.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbai.api.model.account.AccountModel;

public interface AccountModelRepository extends JpaRepository<AccountModel, Long> {
    Optional<AccountModel> findByIdentifiantAndPassword(String identifiant, String password);

    Optional<AccountModel> findByToken(String token);
}
