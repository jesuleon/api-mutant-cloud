package com.mercadolibre.xmen.domain.model;

import com.google.type.Date;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import java.util.Arrays;
import java.util.Calendar;

/**
 * Created by jesus.leon on 27/05/18.
 */
@Entity
public class DnaSequence {
    @Id
    private long id;
    private String[] dna;
    private boolean mutant;

    public DnaSequence(String[] dna, boolean mutant) {
        id = Calendar.getInstance().getTime().getTime();

        this.dna = dna;
        this.mutant = mutant;
    }

    public long getId() {
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

    @Override public String toString() {
        return "DnaSequence{" +
                "id='" + id + '\'' +
                ", dna=" + Arrays.toString(dna) +
                ", mutant=" + mutant +
                '}';
    }
}
