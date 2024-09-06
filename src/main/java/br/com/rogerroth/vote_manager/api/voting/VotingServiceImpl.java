package br.com.rogerroth.vote_manager.api.voting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import br.com.rogerroth.vote_manager.api.agenda.AgendaDTO;
import br.com.rogerroth.vote_manager.api.agenda.AgendaService;
import br.com.rogerroth.vote_manager.api.session.SessionDTO;
import br.com.rogerroth.vote_manager.api.session.SessionService;
import br.com.rogerroth.vote_manager.client.ValidateAssociatedRestClient;
import br.com.rogerroth.vote_manager.config.exception.CpfInvalidException;
import br.com.rogerroth.vote_manager.config.exception.InvalidSessionException;
import br.com.rogerroth.vote_manager.config.exception.VoteDeniedException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VotingServiceImpl implements VotingService {
  private final VotingRepository votingRepository;
  
  private final SessionService sessionService;
  
  private final AgendaService agendaService;
  
  private final ValidateAssociatedRestClient validateAssociatedRestClient;
  
  @Autowired
  public VotingServiceImpl(VotingRepository votingRepository, SessionService sessionService, AgendaService agendaService, ValidateAssociatedRestClient validateAssociatedRestClient) {
    this.votingRepository = votingRepository;
    this.sessionService = sessionService;
    this.agendaService = agendaService;
    this.validateAssociatedRestClient = validateAssociatedRestClient;
  }

  @Override
  public VotingDTO addVote(ReceiveVoteDTO receiveVoteDTO) {
    validateAssociated(receiveVoteDTO.getCpfAssociated());

    SessionDTO sessionDTO = this.sessionService.findOpeningSession(receiveVoteDTO.getIdAgenda());

    log.info("Voting: member: {}, vote: {}, agenda: {}.", receiveVoteDTO.getCpfAssociated(), receiveVoteDTO.getVote(), sessionDTO.getIdAgenda());

    Optional<VotingModel> votingOptional = this.votingRepository.findByIdSessionAndCpfAssociated(sessionDTO.getId(), receiveVoteDTO.getCpfAssociated());

    if (votingOptional.isPresent()) {
        log.error("Vote denied! member {} has already voted on the agenda {}!", receiveVoteDTO.getCpfAssociated(), receiveVoteDTO.getIdAgenda());
        throw new VoteDeniedException("Vote denied! member " + receiveVoteDTO.getCpfAssociated() +" has already voted on the agenda " + receiveVoteDTO.getIdAgenda() + "!");
    }
    
    VotingModel votingModel = this.votingRepository.save(VotingConverter.converterDTOEntity(receiveVoteDTO, sessionDTO));
    return VotingConverter.converterEntityDTO(votingModel);
  }

  private void validateAssociated(String cpf) {
    try {
        if (!this.validateAssociatedRestClient.validateAssociated(cpf)) {
            throw new VoteDeniedException("The associated " + cpf + " does not have voting permission for the agenda.");
        }
    } catch (CpfInvalidException err) {
        log.error(err.getMessage());
        throw err;
    }
}

  @Override
  public ResultVotingDTO findResultVoting(Integer idSession) {
    SessionDTO sessionDTO = this.sessionService.findSession(idSession);
    
    if (LocalDateTime.now().isBefore(sessionDTO.getEndAt())) {
        log.error("Attempt to consult the vote of an open session.");
        throw new InvalidSessionException("The session is still open, please wait for it to end before requesting the vote count! Closing date and time:" + sessionDTO.getEndAt());
    }
    
    List<VotingModel> listOfVotes = this.votingRepository.findByIdSession(idSession);
    AgendaDTO agendaDTO = this.agendaService.findAgenda(sessionDTO.getIdAgenda());
    
    return VotingConverter.converterVoteListToResultVoting(listOfVotes, agendaDTO.getDescription());
  }
}
