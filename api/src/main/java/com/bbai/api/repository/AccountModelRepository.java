package com.bbai.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbai.api.model.Account.accountModel;

public interface AccountModelRepository extends JpaRepository<accountModel, Long> {
    Optional<accountModel> findByIdentifiantAndPassword(String identifiant, String password);

    Optional<accountModel> findByToken(String token);
}
