package com.mercadolibre.mutants.controller;

import com.mercadolibre.mutants.exception.ValidatorSequenceException;
import com.mercadolibre.mutants.services.MutantService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class MutantControllerTest {

    @Mock
    MutantService mutantService;

    MutantController mutantController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mutantController = new MutantController(mutantService);
    }


    @Test
    void getMutantOk() throws ValidatorSequenceException {
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CAGTGC");
        dna.add("TTATGT");
        dna.add("AGAAGG");
        dna.add("CCCCTA");
        dna.add("TCACTG");

        when(mutantService.isMutant(dna)).thenReturn(true);
        ResponseEntity result = mutantController.detectMutant(dna);
        Assert.assertEquals(new ResponseEntity<>(HttpStatus.OK), result);
    }

    @Test
    void getMutantFail() throws ValidatorSequenceException {
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CTGTAC");
        dna.add("TTATGT");
        dna.add("AGAAGG");
        dna.add("CTCCTA");
        dna.add("TCACTG");

        when(mutantService.isMutant(dna)).thenReturn(false);
        ResponseEntity result = mutantController.detectMutant(dna);
        Assert.assertEquals(new ResponseEntity<>(HttpStatus.FORBIDDEN), result);
    }

    @Test
    void getMutantFailException() throws ValidatorSequenceException {
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");

        when(mutantService.isMutant(dna)).thenThrow(ValidatorSequenceException.class);
        ResponseEntity result = mutantController.detectMutant(dna);
        Assert.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST), result);
    }
}
