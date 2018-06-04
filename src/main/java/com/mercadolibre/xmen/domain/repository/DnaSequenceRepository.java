package com.mercadolibre.xmen.domain.repository;

import com.mercadolibre.xmen.domain.model.DnaSequence;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.UUID;

/**
 * Created by jesus.leon on 29/05/18.
 */
public interface DnaSequenceRepository extends MongoRepository<DnaSequence, UUID> {
    long countByMutant(boolean mutant);
}
