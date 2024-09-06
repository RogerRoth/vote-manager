package br.com.rogerroth.vote_manager.api.voting;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReceiveVoteDTO {
  private Integer id;
    
  @NotNull(message = "An agenda must be informed")
  private Integer idAgenda;
  
  @NotNull(message = "A member must be informed")
  private String cpfAssociated;
  
  @NotNull(message = "The voting option must be informed")
  private Boolean vote;
}
