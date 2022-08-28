package com.mercadolibre.mutants.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatsModel {

    @JsonProperty("count_mutant_dna")
    private long countMutantDna;

    @JsonProperty("count_human_dna")
    private long countHumanDna;

    private double ratio;
}
