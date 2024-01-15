package com.bbai.api.model.assemblage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Pompe {
    @Id
    @GeneratedValue
    private long id;

    private String marque;
    private String numSerie;
}
