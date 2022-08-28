package com.mercadolibre.mutants.services;

import com.mercadolibre.mutants.exception.ValidatorSequenceException;
import com.mercadolibre.mutants.services.impl.MutantServiceImpl;
import com.mercadolibre.mutants.utils.ValidatorSequenceDna;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

class MutantServiceTest {

    MutantServiceImpl mutantServiceImpl;
    @Mock
    ValidatorSequenceDna validatorSequenceDna;
    @Mock
    DnaSequenceService dnaSequenceService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mutantServiceImpl = new MutantServiceImpl(dnaSequenceService, validatorSequenceDna);
    }

    @Test
    public void isMutantOk() throws ValidatorSequenceException {
        List<String> dna = new ArrayList<>();
        ValidatorSequenceDna validatorSequenceDna = mock(ValidatorSequenceDna.class);
        dna.add("ATGCGA");
        dna.add("CAGTGC");
        dna.add("TTATGT");
        dna.add("AGAAGG");
        dna.add("CCCTTA");
        dna.add("TCACTG");

        doNothing().when(validatorSequenceDna).validateSizeArrayDna(isA(ArrayList.class));
        doNothing().when(validatorSequenceDna).validateSameRows(isA(ArrayList.class));
        doNothing().when(validatorSequenceDna).validateNumbersAndLettersSequenceDna(isA(ArrayList.class));

        boolean isMutant = mutantServiceImpl.isMutant(dna);
        Assert.assertEquals(true, isMutant);

    }

}
