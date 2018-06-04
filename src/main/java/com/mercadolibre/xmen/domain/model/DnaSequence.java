package com.mercadolibre.xmen.domain.model;

import java.util.UUID;

/**
 * Created by jesus.leon on 27/05/18.
 */
public class DnaSequence {
    private UUID id;
    private String[] dna;
    private boolean mutant;

    public DnaSequence(String[] dna, boolean mutant) {
        id = UUID.randomUUID();

        this.dna = dna;
        this.mutant = mutant;
    }

    public UUID getId() {
        return id;
    }

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }

    public boolean isMutant() {
        return mutant;
    }

    public void setMutant(boolean mutant) {
        this.mutant = mutant;
    }
}
