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
                // FIXME jesus.leon possible memory error in the future
                List<DnaSequence> streamList = ObjectifyService.ofy().load().type(DnaSequence.class).list();

                result[0] = streamList.stream().filter(c -> c.isMutant()).count();
                result[1] = streamList.stream().filter(c -> !c.isMutant()).count();
            }
        });

        return new Stats(result[0], result[1]);
    }
}
