package com.bbai.api.model.assemblage;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Assemblage {
    @Id
    private long id;

    @OneToOne
    private Moteur moteur;

    @OneToOne
    private Pompe pompe;

    @OneToOne
    private Reducteur reducteur;

    @OneToOne
    private Ventilateur ventilateur;
}
