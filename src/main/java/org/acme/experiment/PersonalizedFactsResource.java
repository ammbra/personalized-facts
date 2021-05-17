package org.acme.experiment;

import org.acme.experiment.external.FactsService;
import org.acme.experiment.dto.PersonalizedFactDTO;
import org.acme.experiment.dto.FactDTO;
import org.eclipse.microprofile.graphql.DefaultValue;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

@Path("/api")
public class PersonalizedFactsResource {

    @Inject
    @RestClient
    FactsService factsService;


    @GET
    @Path("/animal")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<FactDTO> getByType(@QueryParam("type") String type) {
        return factsService.getByType(type);
    }

    @GET
    @Path("/animal-async")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<FactDTO> getByTypeAsync(@QueryParam("type") String type) throws ExecutionException, InterruptedException {
        return CompletableFuture.supplyAsync(() -> factsService.getByType(type)).get();
    }

    @GET
    @Path("/animal-type-async")
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<Set<FactDTO>> getByTypeAndAmount(@QueryParam("type") String type, @QueryParam("amount") Integer amount) throws ExecutionException, InterruptedException {
        return factsService.getByTypeAsync(type, amount);
    }

    @GET
    @Path("/animal-async/{factId}/{randomness}")
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<PersonalizedFactDTO> getFactAsync(@PathParam("factId") String factId, @PathParam("randomness")@DefaultValue("0.03") Double randomness) {
        return factsService.getByFactIDAsync(factId);
    }



}