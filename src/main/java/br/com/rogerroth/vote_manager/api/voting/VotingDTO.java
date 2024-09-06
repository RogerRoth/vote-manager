package br.com.rogerroth.vote_manager.api.voting;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VotingDTO {
  private Integer id;
  private Integer idSession;
  private String cpfAssociated;
  private Boolean vote;
}
