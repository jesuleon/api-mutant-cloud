package com.mercadolibre.xmen.domain.service;

import com.mercadolibre.xmen.domain.model.DnaMutantValue;
import com.mercadolibre.xmen.domain.model.DnaSequence;
import com.mercadolibre.xmen.domain.model.Stats;
import com.mercadolibre.xmen.domain.repository.DnaSequenceRepository;
import org.springframework.stereotype.Service;

/**
 * Created by jesus.leon on 27/05/18.
 */
@Service
public class DnaService {
    private final DnaSequenceRepository dnaSequenceRepository;

    public DnaService(DnaSequenceRepository dnaSequenceRepository) {
        this.dnaSequenceRepository = dnaSequenceRepository;
    }

    /**
     * Posts a mutant
     *
     * @param dna
     * @return true if dnaSequence is a mutant, false in other case
     */
    public DnaSequence postDna(String dna[]) {
        boolean isMutant = isMutant(dna);

        DnaSequence dnaSequence = new DnaSequence(dna, isMutant);

        dnaSequenceRepository.save(dnaSequence);

        return dnaSequence;
    }

    public void deleteAll() {
        dnaSequenceRepository.deleteAll();
    }

    /**
     * Returns stats about all dna
     *
     * @return StatsResponse showing dna's stats
     */
    public Stats stats() {
        long mutants = dnaSequenceRepository.countByMutant(true);
        long humans = dnaSequenceRepository.countByMutant(false);

        return new Stats(mutants, humans);
    }

    private boolean isMutant(String[] dna) {
        if (dna.length < 4) {
            return false;
        }

        DnaTranspose dnaTranspose = transpose(dna);

        int count = isValidSeq(dnaTranspose.diagonal) ? 1 : 0;

        for (int i = 0; i < dna.length; i++) {
            if (isValidSeq(dna[i])) {
                count++;
            }

            if (count <= 1 && isValidSeq(dnaTranspose.vertical[i])) {
                count++;
            }

            if (count > 1) {
                break;
            }
        }

        return count > 1;
    }

    private boolean isValidSeq(String seq) {
        if (seq.contains(DnaMutantValue.AAAA.name()) ||
                seq.contains(DnaMutantValue.CCCC.name()) ||
                seq.contains(DnaMutantValue.TTTT.name()) |
                        seq.contains(DnaMutantValue.GGGG.name())) {
            return true;
        } else {
            return false;
        }
    }

    private DnaTranspose transpose(String[] dna) {
        String[] result = new String[dna.length];
        StringBuilder diagonal = new StringBuilder("");

        for (int j = 0; j < dna.length; j++) {
            StringBuilder seq = new StringBuilder("");

            for (int i = 0; i < dna.length; i++) {
                seq.append(dna[i].substring(j, j + 1));

                if (i == j) {
                    diagonal.append(dna[i].substring(j, j + 1));
                }
            }

            result[j] = seq.toString();
        }

        return new DnaTranspose(result, diagonal.toString());
    }

    private class DnaTranspose {
        String[] vertical;
        String diagonal;

        public DnaTranspose(String[] vertical, String diagonal) {
            this.vertical = vertical;
            this.diagonal = diagonal;
        }
    }
}
