package com.mercadolibre.mutants.services;

import com.mercadolibre.mutants.exception.ValidatorSequenceException;

import java.util.List;

public interface MutantService {

    boolean isMutant(List<String> dna) throws ValidatorSequenceException;
}
