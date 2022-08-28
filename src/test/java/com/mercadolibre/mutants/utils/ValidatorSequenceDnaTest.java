package com.mercadolibre.mutants.utils;

import com.mercadolibre.mutants.exception.ValidatorSequenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

public class ValidatorSequenceDnaTest {

    ValidatorSequenceDna validatorSequenceDna;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
            validatorSequenceDna = new ValidatorSequenceDna();
        }


    @Test
    public void validateNumbersAndLettersOk() throws ValidatorSequenceException {
            List<String> dna = new ArrayList<>();
        ValidatorSequenceDna validatorSequenceDna = mock(ValidatorSequenceDna.class);
            dna.add("ATGCGA");
            dna.add("CAGTGC");
            dna.add("TTATGT");
            dna.add("AGAAGG");
            dna.add("CCCTTA");
            dna.add("TCACTG");

            doNothing().when(validatorSequenceDna).validateNumbersAndLettersSequenceDna(isA(ArrayList.class));
            validatorSequenceDna.validateNumbersAndLettersSequenceDna(dna);
            verify(validatorSequenceDna, times(1)).validateNumbersAndLettersSequenceDna(dna);
        }

    @Test
    public void validateNumbersFailAndLettersOk() {
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
        ValidatorSequenceDna validatorSequenceDna = mock(ValidatorSequenceDna.class);
        dna.add("ATGCGA");
        dna.add("CAGTGC");
        dna.add("TTATGT");
        dna.add("AGAAGG");
        dna.add("CCCTTA");
        dna.add("TCACTG");

        doNothing().when(validatorSequenceDna).validateSizeArrayDna(isA(ArrayList.class));
        validatorSequenceDna.validateSizeArrayDna(dna);
        verify(validatorSequenceDna, times(1)).validateSizeArrayDna(dna);
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


    @Test
    public void validateSameRowsOk() throws ValidatorSequenceException {
        List<String> dna = new ArrayList<>();
        ValidatorSequenceDna validatorSequenceDna = mock(ValidatorSequenceDna.class);
        dna.add("ATGCGA");
        dna.add("CAGTGC");
        dna.add("TTATGT");
        dna.add("AGAAGG");
        dna.add("CCCTTA");
        dna.add("TCACTG");

        doNothing().when(validatorSequenceDna).validateSameRows(isA(ArrayList.class));
        validatorSequenceDna.validateSameRows(dna);
        verify(validatorSequenceDna, times(1)).validateSameRows(dna);
    }

    @Test
    public void validateSameRowsFail() throws ValidatorSequenceException {
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CAGTG");
        dna.add("TTATGT");
        dna.add("AGGG");
        dna.add("CCCTTA");
        dna.add("TCACTG");

        assertThrows(ValidatorSequenceException.class, () -> validatorSequenceDna.validateSameRows(dna));
    }

}
