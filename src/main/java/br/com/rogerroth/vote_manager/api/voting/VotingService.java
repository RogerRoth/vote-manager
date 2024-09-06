package br.com.rogerroth.vote_manager.api.voting;


public interface VotingService {
  
  public VotingDTO addVote(ReceiveVoteDTO receiveVoteDTO);

  public ResultVotingDTO findResultVoting(Integer idSession);
}
