package org.acme.experiment.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PersonalizedFactDTO extends FactDTO {

    public Double randomness;
    public UserDTO user;

}
