package com.bbai.api.model.assemblage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Reducteur {
    @Id
    @GeneratedValue
    private long id;

    private String marque;
    private String numSerie;

    //@ManyToOne
    //private Assemblage assemblage;
}
