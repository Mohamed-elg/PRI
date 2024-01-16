package com.bbai.api.model;

import com.bbai.api.model.assemblage.Assemblage;
import com.bbai.api.model.client.ClientModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class FicheMeca {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String numeroDossier;

    @OneToOne(cascade = CascadeType.ALL)
    private ClientModel client;

    @OneToOne(cascade = CascadeType.ALL)
    private Assemblage assemblage;

    @ManyToMany
    private List<Option> options;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateCreation;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate lastModification;

    private String modifiedBy;

}
