package com.mercadolibre.mutants.services;

import com.mercadolibre.mutants.persistence.entities.Dna;

import java.util.List;

public interface DnaSequenceService {

    Dna save(String dnaSequence, boolean isMutant);

    List<Dna> getStatsDnaVerified();
}
