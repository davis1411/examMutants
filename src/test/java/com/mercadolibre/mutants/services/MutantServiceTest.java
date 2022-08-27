package com.mercadolibre.mutants.services;

import com.mercadolibre.mutants.exception.ValidatorSequenceException;
import com.mercadolibre.mutants.services.impl.MutantServiceImpl;
import com.mercadolibre.mutants.utils.ValidatorSequenceDna;
import org.junit.Assert;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class MutantServiceTest {

    MutantServiceImpl mutantServiceImpl;

    @Mock
    ValidatorSequenceDna validatorSequenceDna;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
        mutantServiceImpl = new MutantServiceImpl();
        validatorSequenceDna = new ValidatorSequenceDna();
    }

    //@Test
    public void searchSequenceHorizontal() throws ValidatorSequenceException {
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CAGTGC");
        dna.add("TTATGT");
        dna.add("AGAAGG");
        dna.add("CCCTTA");
        dna.add("TCACTG");

        //when(mutantServiceImpl, "searchSequenceHorizontal", dna).thenReturn("");

       boolean isMutant = mutantServiceImpl.isMutant(dna);
       Assert.assertEquals(true, isMutant);


        //assertThrows(doNothing(), () -> validatorSequenceDna.validateNumbersAndLettersSequenceDna(dna));
    }

}
