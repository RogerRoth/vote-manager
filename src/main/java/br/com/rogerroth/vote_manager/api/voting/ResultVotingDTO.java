package br.com.rogerroth.vote_manager.api.voting;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultVotingDTO {
  private String agenda;
  private Integer totalYes;
  private Integer totalNo;
}
