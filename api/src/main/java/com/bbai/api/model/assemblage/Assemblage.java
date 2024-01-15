package com.bbai.api.model.assemblage;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Assemblage {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Moteur> moteurs;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Pompe> pompes;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Reducteur> reducteurs;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Ventilateur> ventilateurs;
}
