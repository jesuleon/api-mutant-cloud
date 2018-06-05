package com.mercadolibre.xmen.controller;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.LoadResult;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import com.mercadolibre.xmen.api.DnaRequest;
import com.mercadolibre.xmen.api.StatsResponse;
import com.mercadolibre.xmen.domain.model.DnaSequence;
import com.mercadolibre.xmen.domain.model.Stats;
import com.mercadolibre.xmen.domain.service.DnaSequenceService;
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
    private final DnaSequenceService dnaSequenceService;

    @Autowired
    public DnaController(DnaSequenceService dnaSequenceService) {
        this.dnaSequenceService = dnaSequenceService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> postDna(
            @Valid @DnaInRange @RequestBody DnaRequest dnaRequest) {
        //        if (dnaSequenceService.postDna(dnaRequest.getDna()).isMutant()) {
        //            return ResponseEntity.ok().build();
        //        } else {
        //            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        //        }

        DnaSequence dnaSequence = dnaSequenceService.postDna(dnaRequest.getDna());

        if (dnaSequence.isMutant()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<StatsResponse> stats() {
        return ResponseEntity.ok(transform(dnaSequenceService.stats()));
    }

    private StatsResponse transform(Stats stats) {
        return new StatsResponse(stats.getMutants(), stats.getHumans(), stats.getRatio());
    }
}
