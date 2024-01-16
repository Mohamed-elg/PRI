package com.bbai.api.model;

import com.bbai.api.model.assemblage.Assemblage;
import com.bbai.api.model.client.ClientModel;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class FicheMeca {
    @Id
    @GeneratedValue
    private long id;

    private LocalDate dateCreation;

    @OneToOne(cascade = CascadeType.ALL)
    private Assemblage assemblage;

    @OneToOne(cascade = CascadeType.ALL)
    private ClientModel client;

    private LocalDate lastModification;

    private String modifiedBy;

}
