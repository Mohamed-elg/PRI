package com.bbai.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbai.api.model.Client.ClientModel;

public interface ClientRepository extends JpaRepository<ClientModel, Long> {

    Optional<ClientModel> findByRef(String ref);

}