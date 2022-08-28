package com.mercadolibre.mutants.persistence.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "sequencedna")
public class Dna implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sequence")
    private Long id;

    @Column(name = "sequence_dna")
    private String sequence;

    @Column(name = "is_mutant")
    private Boolean isMutant;

    @Column(name = "fe_analysis")
    private LocalDateTime feAnalysis;
}