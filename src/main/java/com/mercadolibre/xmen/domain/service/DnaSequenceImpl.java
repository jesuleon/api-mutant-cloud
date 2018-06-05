package com.mercadolibre.xmen.domain.service;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import com.mercadolibre.xmen.domain.model.DnaSequence;
import com.mercadolibre.xmen.domain.model.Stats;
import com.mercadolibre.xmen.domain.repository.DnaSequenceRepository;
import java.util.List;

/**
 * Created by jesus.leon on 04/06/18.
 */
public class DnaSequenceImpl implements DnaSequenceRepository {

    public void DnaSequenceImpl() {
    }

    @Override public DnaSequence save(DnaSequence dnaSequence) {
        ObjectifyService.init();
        ObjectifyService.register(DnaSequence.class);
        final DnaSequence[] result = new DnaSequence[1];

        ObjectifyService.run(new VoidWork() {

            public void vrun() {
                Key<DnaSequence> key = ObjectifyService.ofy().save().entity(dnaSequence).now();

                result[0] = ObjectifyService.ofy().load().type(DnaSequence.class).id(key.getId()).now();
            }
        });

        return result[0];

    }

    public void deleteAll() {
        ObjectifyService.init();
        ObjectifyService.register(DnaSequence.class);
        ObjectifyService.run(new VoidWork() {
            public void vrun() {
                List<Key<DnaSequence>> keys = ObjectifyService.ofy().load().type(DnaSequence.class).keys().list();

                ObjectifyService.ofy().delete().keys(keys).now();
            }
        });
    }

    public Stats stats() {
        ObjectifyService.init();
        ObjectifyService.register(DnaSequence.class);
        final long[] result = new long[2];

        ObjectifyService.run(new VoidWork() {

            public void vrun() {
                result[0] = ObjectifyService.ofy().load().type(DnaSequence.class).filter("mutant", "true").list()
                        .size();

                result[1] = ObjectifyService.ofy().load().type(DnaSequence.class).filter("mutant", "false").list()
                        .size();
            }
        });

        return new Stats(result[0], result[1]);
    }
}
