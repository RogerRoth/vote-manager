package br.com.rogerroth.vote_manager.api.voting;
import java.util.List;
import br.com.rogerroth.vote_manager.api.session.SessionDTO;

public class VotingConverter {
  public static VotingDTO converterEntityDTO(VotingModel entity) {
    return VotingDTO.builder()
            .id(entity.getId())
            .cpfAssociated(entity.getCpfAssociated())
            .idSession(entity.getIdSession())
            .vote(entity.getVote())
            .build();
  }

  public static ResultVotingDTO converterVoteListToResultVoting(List<VotingModel> list, String agenda) {
      Integer yes = 0;
      Integer no = 0;
      
      yes = list.stream().filter((voting) -> (voting.getVote().equals(Boolean.TRUE))).map((_item) -> 1).reduce(yes, Integer::sum);
      no = list.stream().filter((voting) -> (voting.getVote().equals(Boolean.FALSE))).map((_item) -> 1).reduce(no, Integer::sum);
      
      return ResultVotingDTO.builder()
              .agenda(agenda)
              .totalYes(yes)
              .totalNo(no)
              .build();
  }

  public static VotingModel converterDTOEntity(ReceiveVoteDTO receiveVoteDTO, SessionDTO sessionDTO) {
    return VotingModel.builder()
            .cpfAssociated(receiveVoteDTO.getCpfAssociated())
            .idSession(sessionDTO.getId())
            .vote(receiveVoteDTO.getVote())
            .build();
  }
}
