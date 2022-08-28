package com.mercadolibre.mutants.controller;

import com.mercadolibre.mutants.exception.ValidatorSequenceException;
import com.mercadolibre.mutants.services.MutantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MutantController {


    private final MutantService mutantService;

    @Autowired
    public MutantController(MutantService mutantService) {
        this.mutantService = mutantService;
    }

    private static final Logger log = LoggerFactory.getLogger(MutantController.class);

    @RequestMapping(value = "/mutant", method = RequestMethod.POST)
    public ResponseEntity<String> detectMutant(@RequestBody List<String> dna) {
        ResponseEntity<String> responseEntity = null;
        try {
            if (this.mutantService.isMutant(dna)) {
                responseEntity = new ResponseEntity<>(HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (ValidatorSequenceException ex) {
            responseEntity = new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Ocurrio un error detectando mutantes");
        }
        return responseEntity;
    }
}
