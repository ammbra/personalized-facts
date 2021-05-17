package org.acme.experiment.dto;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
public class StatusDTO {
   public boolean verified;
   public int sentCount;
   public String feedback;
}
