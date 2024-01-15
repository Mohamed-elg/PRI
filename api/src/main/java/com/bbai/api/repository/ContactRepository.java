package com.bbai.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbai.api.model.Client.ContactModel;

public interface ContactRepository extends JpaRepository<ContactModel, Long> {

}
