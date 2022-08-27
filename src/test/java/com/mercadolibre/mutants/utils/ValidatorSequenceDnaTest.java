package com.mercadolibre.mutants.utils;

import com.mercadolibre.mutants.exception.ValidatorSequenceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;

public class ValidatorSequenceDnaTest {

    ValidatorSequenceDna validatorSequenceDna;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
            validatorSequenceDna = new ValidatorSequenceDna();
        }


    //Test
    public void validateNumbersAndLettersOk() throws ValidatorSequenceException {
            List<String> dna = new ArrayList<>();
            dna.add("ATGCGA");
            dna.add("CAGTGC");
            dna.add("TTATGT");
            dna.add("AGAAGG");
            dna.add("CCCTTA");
            dna.add("TCACTG");

           // verify(validatorSequenceDna.validateNumbersAndLettersSequenceDna(dna),)

            //Assert.assertTrue(Void, validatorSequenceDna.validateNumbersAndLettersSequenceDna(dna));

        Assert.assertEquals("",doNothing().
                doThrow(new ValidatorSequenceException())
                .when(new ValidatorSequenceException("")).getMessage());


       // assertThrows(doNothing(), () -> validatorSequenceDna.validateNumbersAndLettersSequenceDna(dna));
        }

    @Test
    public void validateNumbersFailAndLettersOk() throws ValidatorSequenceException {
        List<String> dna = new ArrayList<>();
        dna.add("123456");
        dna.add("CAGTGC");
        dna.add("TTATGT");
        dna.add("AGAAGG");
        dna.add("CCCTTA");
        dna.add("TCACTG");

        assertThrows(ValidatorSequenceException.class, () -> validatorSequenceDna.validateNumbersAndLettersSequenceDna(dna));
    }

    @Test
    public void validateNumbersOKAndLettersFail() throws ValidatorSequenceException {
        List<String> dna = new ArrayList<>();
        dna.add("ZZZZZZ");
        dna.add("CAGTGC");
        dna.add("TTATGT");
        dna.add("AGAAGG");
        dna.add("XXXXXX");
        dna.add("TCACTG");

        assertThrows(ValidatorSequenceException.class, () -> validatorSequenceDna.validateNumbersAndLettersSequenceDna(dna));
    }

    @Test
    public void validateNumbersFailAndLettersFail() throws ValidatorSequenceException {
        List<String> dna = new ArrayList<>();
        dna.add("ZZZZZZ");
        dna.add("111111");
        dna.add("TTATGT");
        dna.add("222222");
        dna.add("XXXXXX");
        dna.add("TCACTG");

        assertThrows(ValidatorSequenceException.class, () -> validatorSequenceDna.validateNumbersAndLettersSequenceDna(dna));
    }

    @Test
    public void validateSizeArrayOk() throws ValidatorSequenceException {
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CAGTGC");
        dna.add("TTATGT");
        dna.add("AGAAGG");
        dna.add("CCCTTA");
        dna.add("TCACTG");

        assertThrows(ValidatorSequenceException.class, () -> validatorSequenceDna.validateSizeArrayDna(dna));
    }

    @Test
    public void validateSizeArrayFailRow() throws ValidatorSequenceException {
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CAGTGC");

        assertThrows(ValidatorSequenceException.class, () -> validatorSequenceDna.validateSizeArrayDna(dna));
    }

    @Test
    public void validateSizeArrayFailColumn() throws ValidatorSequenceException {
        List<String> dna = new ArrayList<>();
        dna.add("ATG");
        dna.add("CAG");
        dna.add("TTA");
        dna.add("AGA");
        dna.add("CCA");
        dna.add("TCA");

        assertThrows(ValidatorSequenceException.class, () -> validatorSequenceDna.validateSizeArrayDna(dna));
    }

}
