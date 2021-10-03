package org.acme.experiment.repository;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.acme.experiment.dto.FactDTO;
import org.acme.experiment.model.PersonalizedFact;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class PersonalizedFactsRepositoryITest {

    @Inject
    PersonalizedFactsRepository repository;

    @Test
    void shouldFindBySource() {
        Set<PersonalizedFact> expectedFacts = repository.findBySource(FactDTO.USER, 1);
        assertNotNull(expectedFacts);
        assertFalse(expectedFacts.isEmpty());
    }


    @Test
    void shouldNotFindBySource() {
        Set<PersonalizedFact> expectedFacts = repository.findBySource(FactDTO.API, 1);
        assertNotNull(expectedFacts);
        System.out.println(expectedFacts);
        assertTrue(expectedFacts.isEmpty());
    }
}