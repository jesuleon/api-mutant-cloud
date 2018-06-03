package com.mercadolibre.xmen.controller;

import com.mercadolibre.xmen.api.DnaRequest;
import com.mercadolibre.xmen.api.StatsResponse;
import com.mercadolibre.xmen.domain.model.Stats;
import com.mercadolibre.xmen.domain.service.DnaService;
import com.mercadolibre.xmen.validator.DnaInRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

/**
 * Created by jesus.leon on 13/05/18.
 */
@RestController("/")
@Validated
public class DnaController {
    private final DnaService dnaService;

    @Autowired
    public DnaController(DnaService dnaService) {
        this.dnaService = dnaService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> postDna(
            @Valid @DnaInRange @RequestBody DnaRequest dnaRequest) {
        if (dnaService.postDna(dnaRequest.getDna()).isMutant()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<StatsResponse> stats() {
        return ResponseEntity.ok(transform(dnaService.stats()));
    }

    private StatsResponse transform(Stats stats) {
        return new StatsResponse(stats.getMutants(), stats.getHumans(), stats.getRatio());
    }
}
