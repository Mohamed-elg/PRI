package com.bbai.api.model;

import javax.management.relation.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class accountModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // TODO : change identifiant to username
    @NonNull
    @Column(unique = true)
    @NotBlank(message = "Identifiant is mandatory")
    private String identifiant;

    @NonNull
    @NotBlank(message = "Password is mandatory")
    private String password;
    private String token;

    @NonNull
    @NotBlank(message = "Role is mandatory")
    @Enumerated(EnumType.STRING)
    private ERole role;
}
