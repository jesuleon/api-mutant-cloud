package com.mercadolibre.xmen.service;

import com.mercadolibre.xmen.domain.model.Stats;
import com.mercadolibre.xmen.BasicTest;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by jesus.leon on 27/05/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DnaSequenceServiceTest extends BasicTest {
    @Test
    public void postMutantThenItIsMutant() {
        Assert.assertEquals(true, dnaSequenceService.postDna(MUTANT_DNA).isMutant());
    }

    @Test
    public void postMutantThenItIsNotAMutant() {
        Assert.assertEquals(false, dnaSequenceService.postDna(HUMAN_DNA).isMutant());
    }

    @Test
    public void postMutantWithNxNWithNLessToFour() {
        String[] dna = new String[] { "ATG", "CAG", "TTA" };

        Assert.assertEquals(false, dnaSequenceService.postDna(dna).isMutant());
    }

    @Test
    public void statsWithoutDna() {
        Stats stats = dnaSequenceService.stats();
        Assert.assertEquals(0.0, stats.getRatio(), 0.0000001);
    }

    @Test
    public void statsWithOnlyHumans() {
        dnaSequenceService.postDna(HUMAN_DNA);
        dnaSequenceService.postDna(HUMAN_DNA);
        dnaSequenceService.postDna(HUMAN_DNA);
        Stats stats = dnaSequenceService.stats();

        Assert.assertEquals(0.0, stats.getRatio(), 0.000001);
    }

    @Test
    public void statsWithOnlyMutants() {
        dnaSequenceService.postDna(MUTANT_DNA);
        dnaSequenceService.postDna(MUTANT_DNA);
        dnaSequenceService.postDna(MUTANT_DNA);

        Assert.assertEquals(3.0, dnaSequenceService.stats().getRatio(), 0.000001);
    }

    @Test
    public void stats() {
        dnaSequenceService.postDna(MUTANT_DNA);
        dnaSequenceService.postDna(HUMAN_DNA);
        dnaSequenceService.postDna(HUMAN_DNA);

        Stats stats = dnaSequenceService.stats();

        Assert.assertEquals(0.5, stats.getRatio(), 0.000001);
    }
}
