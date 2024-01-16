package com.bbai.api.repository;

import com.bbai.api.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OptionRepository extends JpaRepository<Option, Long> {
    public Optional<Option> findByLabel(String label);
}