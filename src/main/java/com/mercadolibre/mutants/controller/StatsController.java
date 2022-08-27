package com.mercadolibre.mutants.controller;

import com.mercadolibre.mutants.model.StatsModel;
import com.mercadolibre.mutants.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatsController {
    private final StatsService statsService;

    @Autowired
    public StatsController(StatsService statsService){
        this.statsService = statsService;
    }

    @GetMapping("/stats")
    public StatsModel getStatsDnaVerified() {
        return statsService.getStatsDnaVerified();
    }
}
