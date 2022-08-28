package com.mercadolibre.mutants.services;

import com.mercadolibre.mutants.model.StatsModel;
import com.mercadolibre.mutants.persistence.entities.Dna;
import com.mercadolibre.mutants.services.impl.DnaSequenceServiceImpl;
import com.mercadolibre.mutants.services.impl.StatsServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class StatsServiceTest {

    @Mock
    DnaSequenceServiceImpl dnaSequenceService;

    StatsServiceImpl statsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        statsService = new StatsServiceImpl(dnaSequenceService);
    }


    @Test
    void getStatsOk() {
        Dna dnaMutant = new Dna();
        dnaMutant.setIsMutant(true);
        dnaMutant.setId(1L);
        dnaMutant.setSequence("[ATGCGA, CAGTGC, TTATGT, AGAAGG, CCCCTA, TCACTG]");
        dnaMutant.setFeAnalysis(LocalDateTime.now());

        Dna dnaHuman = new Dna();
        dnaHuman.setIsMutant(true);
        dnaHuman.setId(1L);
        dnaHuman.setSequence("[ATGCGA, CAGTGC, TTATGT, AGAAGG, CCCCTA, TCACTG]");
        dnaHuman.setFeAnalysis(LocalDateTime.now());

        StatsModel statsModel = new StatsModel();
        statsModel.setCountMutantDna(1);
        statsModel.setCountHumanDna(1);
        statsModel.setRatio(1.0);

        List<Dna> dnaListMutant = new ArrayList<>();
        dnaListMutant.add(dnaMutant);

        List<Dna> dnaListHuman = new ArrayList<>();
        dnaListHuman.add(dnaHuman);

        when(dnaSequenceService.getStatsDnaMutants()).thenReturn(dnaListMutant);
        when(dnaSequenceService.getStatsDnaHumans()).thenReturn(dnaListHuman);
        StatsModel result = statsService.getStatsDnaVerified();
        Assert.assertEquals(statsModel.getCountHumanDna(), result.getCountHumanDna());
        Assert.assertEquals(statsModel.getCountMutantDna(), result.getCountMutantDna());
        Assert.assertEquals(statsModel.getRatio(), result.getRatio(), 0.0f);
    }

    @Test
    void getStatsFailMutantsCero() {
        Dna dnaHuman = new Dna();
        dnaHuman.setIsMutant(true);
        dnaHuman.setId(1L);
        dnaHuman.setSequence("[ATGCGA, CAGTGC, TTATGT, AGAAGG, CCCCTA, TCACTG]");
        dnaHuman.setFeAnalysis(LocalDateTime.now());

        StatsModel statsModel = new StatsModel();
        statsModel.setCountMutantDna(0);
        statsModel.setCountHumanDna(1);
        statsModel.setRatio(0.0);

        List<Dna> dnaListMutant = new ArrayList<>();

        List<Dna> dnaListHuman = new ArrayList<>();
        dnaListHuman.add(dnaHuman);

        when(dnaSequenceService.getStatsDnaMutants()).thenReturn(dnaListMutant);
        when(dnaSequenceService.getStatsDnaHumans()).thenReturn(dnaListHuman);
        StatsModel result = statsService.getStatsDnaVerified();
        Assert.assertEquals(statsModel.getCountHumanDna(), result.getCountHumanDna());
        Assert.assertEquals(statsModel.getCountMutantDna(), result.getCountMutantDna());
        Assert.assertEquals(statsModel.getRatio(), result.getRatio(), 0.0f);
    }
}
