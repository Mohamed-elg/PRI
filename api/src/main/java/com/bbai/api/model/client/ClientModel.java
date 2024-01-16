package com.bbai.api.model.client;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@NoArgsConstructor
public class ClientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @NotBlank(message = "Nom is mandatory")
    private String nom;
    @NonNull
    @NotBlank(message = "Ref is mandatory")
    private String ref;

    @NonNull
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ContactModel contact;

}
