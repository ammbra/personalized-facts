package org.acme.experiment.repository;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.acme.experiment.model.PersonalizedFact;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class PersonalizedFactsRepositoryTest {

    @Inject
    PersonalizedFactsRepository repository;

    @Test
    void shouldFindBySource() {
        Set<PersonalizedFact> expectedFacts = repository.findBySource("api", 3);
        assertNotNull(expectedFacts);
        assertFalse(expectedFacts.isEmpty());
    }


    @Test
    void shouldNotFindBySource() {
        Set<PersonalizedFact> expectedFacts = repository.findBySource("web", 3);
        assertNotNull(expectedFacts);
        assertTrue(expectedFacts.isEmpty());
    }
}