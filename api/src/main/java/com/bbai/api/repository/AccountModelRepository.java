package com.bbai.api.repository;

import com.bbai.api.model.accountModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountModelRepository extends JpaRepository<accountModel, Long> {
    Optional<accountModel> findByIdentifiantAndPassword(String identifiant, String password);
}
