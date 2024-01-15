package com.bbai.api.model.assemblage;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Moteur {
    @Id
    @GeneratedValue
    private long Id;
    private String marque;
    private String numSerie;
    @Enumerated(EnumType.STRING)
    private EtypeMoteur typeMoteur;

    //@ManyToOne
    //private Assemblage assemblage;
}
