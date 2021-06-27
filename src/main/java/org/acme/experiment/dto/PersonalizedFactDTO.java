package org.acme.experiment.dto;

import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbCreator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@NoArgsConstructor
public class PersonalizedFactDTO extends FactDTO {

    public Double randomness;
    public UserDTO user;

    @JsonbCreator
    public static CompletionStage<PersonalizedFactDTO> emptyAsync() {
        return CompletableFuture.supplyAsync(() -> new PersonalizedFactDTO());
    }
}
