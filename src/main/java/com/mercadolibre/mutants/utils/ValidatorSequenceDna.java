package com.mercadolibre.mutants.utils;

import com.mercadolibre.mutants.exception.ValidatorSequenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class ValidatorSequenceDna {

    private static final Logger log = LoggerFactory.getLogger(ValidatorSequenceDna.class);

    public void validateNumbersAndLettersSequenceDna(List<String> dna) throws ValidatorSequenceException {
        Scanner scanner = new Scanner(dna.toString());
        try {
            String Letters = "[BDEFHIJKLMNÑOPQRSUVWXYZ]";
            String Numbers = "[0123456789]";
            String validationResultLetters = scanner.findInLine(Letters);
            String validationResultNumbers = scanner.findInLine(Numbers);
            if (validationResultLetters != null || validationResultNumbers != null) {
                throw new ValidatorSequenceException("Contiene caracteres diferentes a A,T,G,C los cuales son los unicos validos para evaluar el adn");
            }
        } finally {
            scanner.close();
        }
    }

    public void validateSameRows(List<String>dna)throws ValidatorSequenceException{
        int numColumns = dna.get(0).length();
        for(int i=0; i < dna.size(); i++){
            if(dna.get(i).length() != numColumns){
                throw new ValidatorSequenceException("Todas las filas no contienen la misma cantidad de caracteres para evaluar la secuencia de adn");
            }
        }
    }

    public void validateSizeArrayDna(List<String> dna) throws ValidatorSequenceException{
            if(dna.size() < 4 || dna.get(0).length() < 4 ){
                throw new ValidatorSequenceException("La secuencia de adn no cumple con el minimo de 4 letras horizontal horizontal o verticalmente para poder ser analizada");
            }
    }
}
