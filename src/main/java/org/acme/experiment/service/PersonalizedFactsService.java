package org.acme.experiment.service;

import org.acme.experiment.dto.PersonalizedFactDTO;
import org.acme.experiment.mapper.PersonalizedFactsMapper;
import org.acme.experiment.model.PersonalizedFact;
import org.acme.experiment.repository.PersonalizedFactsRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.Set;

@Singleton
public class PersonalizedFactsService {

    @Inject
    PersonalizedFactsRepository repository;

    @Transactional
    public void setup(Set<PersonalizedFact> facts) {
        repository.persist(facts);
    }

    public Set<PersonalizedFactDTO> findBySource(String source, Integer size) {
        PersonalizedFactsMapper mapper = new PersonalizedFactsMapper();
        return mapper.mapToDTO(repository.findBySource(source, size));
    }
}
