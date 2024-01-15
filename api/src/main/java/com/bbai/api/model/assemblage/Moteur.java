package com.bbai.api.model.assemblage;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Moteur {
    @Id
    private long Id;
    private String marque;
    private String numSerie;
    @Enumerated(EnumType.STRING)
    private EtypeMoteur typeMoteur;
}
