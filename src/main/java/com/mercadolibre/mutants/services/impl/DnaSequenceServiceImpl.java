package com.mercadolibre.mutants.services.impl;

import com.mercadolibre.mutants.persistence.entities.Dna;
import com.mercadolibre.mutants.persistence.repository.ResultDnaRepository;
import com.mercadolibre.mutants.services.DnaSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DnaSequenceServiceImpl implements DnaSequenceService {

    @Autowired
    private ResultDnaRepository resultDnaRepository;


    @Override
    public Dna save(String dnaSequence, boolean isMutant) {
        Dna dna = new Dna();
        dna.setSequence(dnaSequence);
        dna.setIsMutant(isMutant);
        dna.setFeAnalysis(LocalDateTime.now());
        return this.resultDnaRepository.save(dna);
    }

    @Override
    public List<Dna> getStatsDnaMutants() {
        return resultDnaRepository.findByIsMutant(true);
    }

    @Override
    public List<Dna> getStatsDnaHumans() {
        return resultDnaRepository.findByIsMutant(false);
    }
}
