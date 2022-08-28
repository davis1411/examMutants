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
    private DnaSequenceServiceImpl dnaSequenceService;

    public StatsServiceImpl(DnaSequenceServiceImpl dnaSequenceService) {
        this.dnaSequenceService = dnaSequenceService;
    }

    @Override
    public StatsModel getStatsDnaVerified() {
        List<Dna> dnaVerifiedMutants = dnaSequenceService.getStatsDnaMutants();
        List<Dna> dnaVerifiedHumans = dnaSequenceService.getStatsDnaHumans();
        return countMutant(dnaVerifiedMutants, dnaVerifiedHumans);
    }

    private StatsModel countMutant(List<Dna> dnaVerifiedMutants, List<Dna> dnaVerifiedHumans) {
        StatsModel statsModel = new StatsModel();
        Double ratio = 0.0;
        int countMutants = dnaVerifiedMutants.size();
        int countHumans = dnaVerifiedHumans.size();
        if (countMutants > 0) {
            ratio = (double) countMutants / countHumans;
        }

        statsModel.setCountMutantDna(countMutants);
        statsModel.setCountHumanDna(countHumans);
        statsModel.setRatio(ratio);
        return statsModel;
    }
}
