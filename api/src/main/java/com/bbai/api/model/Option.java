package com.bbai.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue
    Long id;

    @Column(unique = true)
    private String label;

    public Option(String label){
        this.label = label;
    }
}