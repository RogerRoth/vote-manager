package br.com.rogerroth.vote_manager.api.session;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionOpeningDTO {
  @NotNull(message = "An agenda must be informed")
  private Integer idAgenda;
  
  private Integer duration;
}
