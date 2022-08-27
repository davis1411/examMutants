package com.mercadolibre.mutants.services.impl;

import com.mercadolibre.mutants.model.StatsModel;
import com.mercadolibre.mutants.persistence.entities.Dna;
import com.mercadolibre.mutants.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsServiceImpl implements StatsService {
    @Autowired
    private  DnaSequenceServiceImpl dnaSequenceService ;
    @Override
    public StatsModel getStatsDnaVerified() {
        List<Dna> dnaVerified = dnaSequenceService.getStatsDnaVerified();
        return countMutant(dnaVerified);
    }

    private StatsModel countMutant(List<Dna> dnaVerified) {
        StatsModel statsModel = new StatsModel();
        Long contMutant = dnaVerified.stream().filter(m -> m.getIsMutant()).count();
        Long contHuman = dnaVerified.stream().filter(h -> !h.getIsMutant()).count();
        Double ratio = (double)contMutant/contHuman;
        statsModel.setCountMutantDna(contMutant);
        statsModel.setCountHumanDna(contHuman);
        statsModel.setRatio(ratio);
        return statsModel;
    }
}
