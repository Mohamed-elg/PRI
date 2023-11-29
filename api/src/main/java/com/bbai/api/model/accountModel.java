package com.bbai.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class accountModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // TODO : change identifiant to username
    @Column(unique = true)
    private String identifiant;
    private String password;
    private String token;
}
