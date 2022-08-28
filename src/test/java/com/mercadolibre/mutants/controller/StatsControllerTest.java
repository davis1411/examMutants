package com.mercadolibre.mutants.controller;

import com.mercadolibre.mutants.exception.ValidatorSequenceException;
import com.mercadolibre.mutants.model.StatsModel;
import com.mercadolibre.mutants.services.DnaSequenceService;
import com.mercadolibre.mutants.services.StatsService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class StatsControllerTest {

    @Mock
    StatsService statsService;

    @Mock
    DnaSequenceService dnaSequenceService;

    StatsController statsController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        statsController = new StatsController(statsService);
    }

    @Test
    void getStatusOk() throws ValidatorSequenceException {
        StatsModel statsModel = new StatsModel();
        statsModel.setCountMutantDna(40);
        statsModel.setCountHumanDna(100);
        statsModel.setRatio(0.4);

        when(statsService.getStatsDnaVerified()).thenReturn(statsModel);
        StatsModel result = statsController.getStatsDnaVerified();
        Assert.assertEquals(statsModel, result);
    }


}
