package com.mercadolibre.mutants.controller;

import com.mercadolibre.mutants.services.MutantService;
import org.mockito.Mock;

public class MutantControllerTest {

    @Mock
    MutantService mutantService;

    @Mock
    MutantController mutantController;

    /*@Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mutantController = new MutantController(mutantService);
    }*/


    //@Test
    /*void getMutant_Ok() {
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CAGTGC");
        dna.add("TTATGT");
        dna.add("AGAAGG");
        dna.add("CCCCTA");
        dna.add("TCACTG");
        ResponseEntity result = mutantController.detectMutant(dna);
        Assert.assertEquals(ResponseEntity.ok(HttpStatus.OK), result);
    }*/
}
