package com.bbai.api.model;

import com.bbai.api.model.assemblage.Assemblage;
import com.bbai.api.model.client.ClientModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class FicheMeca {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Assemblage assemblage;

    @OneToOne(cascade = CascadeType.ALL)
    private ClientModel client;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateCreation;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate lastModification;

    private String modifiedBy;

}
