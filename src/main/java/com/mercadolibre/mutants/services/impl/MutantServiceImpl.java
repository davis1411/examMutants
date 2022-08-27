package com.mercadolibre.mutants.services.impl;

import com.mercadolibre.mutants.exception.ValidatorSequenceException;
import com.mercadolibre.mutants.services.DnaSequenceService;
import com.mercadolibre.mutants.services.MutantService;
import com.mercadolibre.mutants.utils.ValidatorSequenceDna;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MutantServiceImpl implements MutantService {
    @Autowired
    private DnaSequenceService dnaSequenceService;

    @Autowired
    private ValidatorSequenceDna validatorSequenceDna;
    private static final Logger log = LoggerFactory.getLogger(MutantServiceImpl.class);

    @Override
    public boolean isMutant(List<String> dna) throws ValidatorSequenceException {
        boolean isMutant = false;
        int contMutants = 0;
            validatorSequenceDna.validateNumbersAndLettersSequenceDna(dna);
            validatorSequenceDna.validateSameRows(dna);
            validatorSequenceDna.validateSizeArrayDna(dna);
                char[][] dnaSequence = createDnaSequence(dna);
                contMutants = searchSequenceHorizontal(dnaSequence);
                if(contMutants < 2) {
                    contMutants = contMutants + searchSequenceVertical(dnaSequence);
                }
                if(contMutants < 2) {
                    contMutants = contMutants + searchSequenceDiagonalLeftToRight(dnaSequence);
                }
                if(contMutants < 2) {
                    contMutants = contMutants + searchSequenceDiagonalRightToLeft(dnaSequence);
                }

                if(contMutants > 1){
                    isMutant =  true;
                }
                log.info("Inicia proceso para guardar en base de datos secuencia de adn con resultado de mutante : " + isMutant + " y secuencia de adn " + dna.toString());
                dnaSequenceService.save(dna.toString(), isMutant);
        return  isMutant;
    }

    private char[][] createDnaSequence(List<String> dna){
        log.info("Creando matriz con secuencia ADN");
        int dnaSize = dna.size();
        char[][] dnaSequence = new char[dnaSize][dnaSize];
        try {
            for (int i = 0; i < dnaSize; i++) {
                for (int j = 0; j < dnaSize; j++) {
                     dnaSequence[i][j] = dna.get(i).charAt(j);
                }
            }
        }catch (Exception e){
            log.error("Error creando matriz con secuencia ADN");
        }
        return dnaSequence;
    }

    private int searchSequenceHorizontal(char[][] dnaSequence){
        log.info("Buscando secuencia de ADN horizontalmente", dnaSequence);
        int cont = 0;
        int contMutant = 0;
        for (int f = 0; f < dnaSequence.length; f++) {
            for (int c = 0; c < dnaSequence.length; c++) {
                int nextVal = c+1;
                if(nextVal < dnaSequence.length){
                    if(dnaSequence[f][c] == dnaSequence[f][nextVal]){
                        cont++;
                        if(cont >= 3){
                            contMutant++;
                            if(contMutant > 1){
                                return contMutant;
                            }
                        }
                    }else{
                        cont=0;
                    }
                }
            }
        }
       return contMutant;
    }

    private int searchSequenceVertical(char[][] dnaSequence){
        log.info("Buscando secuencia de ADN verticalmente", dnaSequence);
        int cont = 0;
        int contMutant = 0;
        for (int c = 0; c < dnaSequence.length; c++) {
            for (int f = 0; f < dnaSequence.length; f++) {
                int nextVal = f+1;
                if(nextVal < dnaSequence.length){
                    if(dnaSequence[f][c] == dnaSequence[nextVal][c]){
                        cont++;
                        if(cont >= 3){
                            contMutant++;
                            if(contMutant > 1){
                                return contMutant;
                            }
                        }
                    }else{
                        cont=0;
                    }
                }
            }
        }
       return contMutant;
    }

    private int searchSequenceDiagonalLeftToRight(char[][] dnaSequence) {
        log.info("Buscando secuencia de ADN diagonal", dnaSequence);
        int fila = dnaSequence.length - 1;
        int col = 0;
        int aux = 1;
        int contMutant = 0;
        int limit = dnaSequence.length;
        List<String> diagonalSequence = new ArrayList<>();
        while (col < dnaSequence.length) {
            for (int i = fila; i < limit && col < limit; i++, col++) {
                diagonalSequence.add(String.valueOf(dnaSequence[i][col]));
            }
            contMutant = contMutant + evaluateMutantForLine(diagonalSequence);
                fila--;
                diagonalSequence.clear();
                if (fila < 0) {
                    fila = 0;
                    col = aux;
                    aux++;
                } else {
                    col = 0;
                }
            }
            return contMutant;
        }


    private int searchSequenceDiagonalRightToLeft(char[][] dnaSequence) {
        log.info("Buscando secuencia de ADN diagonal", dnaSequence);
        int fila = dnaSequence.length - 1;
        int col = dnaSequence.length - 1;
        int aux = dnaSequence.length - 1;
        int contMutant = 0;
        int limit = dnaSequence.length;
        List<String> diagonalSequence = new ArrayList<>();
        while (col >= 0) {
            for (int i = fila; i < limit && col < limit && col >= 0; i++, col--) {
                diagonalSequence.add(String.valueOf(dnaSequence[i][col]));
            }
            contMutant = contMutant + evaluateMutantForLine(diagonalSequence);
            fila--;
            diagonalSequence.clear();
            if (fila < 0) {
                fila = 0;
                    col = aux;
                    aux--;
                col--;
            } else {
                col = dnaSequence.length - 1;;
            }
        }
        return contMutant;
    }

    private int evaluateMutantForLine(List<String> diagonalSequence){
        int cont = 0;
        int contMutant = 0;
        if (diagonalSequence.size() >= 4) {
            for (int k = 0; k < diagonalSequence.size() - 1; k++) {
                int nextVal = k+1;
                if (diagonalSequence.get(k).equals(diagonalSequence.get(nextVal))) {
                    cont++;
                    //el numero 3 equivale a las 4 letras, que serian 3 secuencias de letras iguales
                    if (cont >= 3) {
                        contMutant++;
                        if(contMutant > 1){
                            break;
                        }
                    }
                } else {
                    cont = 0;
                }
            }
        }else{
            diagonalSequence.clear();
        }
        return contMutant;
    }
}
