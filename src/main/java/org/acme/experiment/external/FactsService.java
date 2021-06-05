package org.acme.experiment.external;

import io.quarkus.cache.CacheKey;
import io.quarkus.cache.CacheResult;
import org.acme.experiment.dto.FactDTO;
import org.acme.experiment.dto.PersonalizedFactDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import java.util.Set;
import java.util.concurrent.CompletionStage;


@Path("/facts")
@RegisterRestClient
public interface FactsService {

    @GET
    @Produces("application/json")
    Set<FactDTO> getByType(@QueryParam("animal_type") String animalType);

    @GET
    @Path("random")
    @Produces("application/json")
    CompletionStage<Set<FactDTO>> getByTypeAsync(@QueryParam("animal_type") String animalType, @QueryParam("amount") int amount);

    @GET
    @Path("{factID}")
    @Produces("application/json")
    @CacheResult(cacheName = "animal-fact-async")
    CompletionStage<PersonalizedFactDTO> getByFactIDAsync(@CacheKey @PathParam("factID") String factID);
}
