package org.acme.experiment;

import org.acme.experiment.external.FactsService;
import org.acme.experiment.dto.PersonalizedFactDTO;
import org.acme.experiment.dto.FactDTO;
import org.acme.experiment.service.PersonalizedFactsService;
import org.eclipse.microprofile.graphql.DefaultValue;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

@Path("/api")
@GraphQLApi
public class PersonalizedFactsResource {

    @Inject
    @RestClient
    FactsService factsService;

    @Inject
    PersonalizedFactsService personalizedFactsService;


    @GET
    @Path("fact")
    @Produces(MediaType.APPLICATION_JSON)
    @Query("allAnimalsByType")
    public Set<FactDTO> getByType(@QueryParam("type") String type) {
        return factsService.getByType(type);
    }

    @GET
    @Path("fact-async")
    @Produces(MediaType.APPLICATION_JSON)
    @Query("allAnimalsByTypeAsync")
    public CompletionStage<Set<FactDTO>> getByTypeAsync(@QueryParam("type") String type) {
        return CompletableFuture.supplyAsync(() -> factsService.getByType(type));
    }

    @GET
    @Path("fact-type-async")
    @Produces(MediaType.APPLICATION_JSON)
    @Query("animalByTypeAndAmount")
    public CompletionStage<Set<FactDTO>> getByTypeAndAmount(@QueryParam("type") String type, @QueryParam("amount") Integer amount) throws ExecutionException, InterruptedException {
        return factsService.getByTypeAsync(type, amount);
    }

    @GET
    @Path("fact-async/{factId}/{randomness}")
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<PersonalizedFactDTO> getFactAsync(@PathParam("factId") String factId, @PathParam("randomness")@DefaultValue("0.03") Double randomness) {
        return factsService.getByFactIDAsync(factId);
    }

    @GET
    @Path("fact/{source}/{size}")
    @Query("animalBySource")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<PersonalizedFactDTO> getPaginatedAnimalsBySource(@PathParam("source") String source, @PathParam("size")@DefaultValue("10") Integer size) {
        return personalizedFactsService.findBySource(source, size);
    }


}