package br.com.rogerroth.vote_manager.voting;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.rogerroth.vote_manager.api.agenda.AgendaService;
import br.com.rogerroth.vote_manager.api.session.SessionDTO;
import br.com.rogerroth.vote_manager.api.session.SessionService;
import br.com.rogerroth.vote_manager.api.voting.ReceiveVoteDTO;
import br.com.rogerroth.vote_manager.api.voting.VotingModel;
import br.com.rogerroth.vote_manager.api.voting.VotingRepository;
import br.com.rogerroth.vote_manager.api.voting.VotingServiceImpl;
import br.com.rogerroth.vote_manager.client.ValidateAssociatedRestClient;
import br.com.rogerroth.vote_manager.config.exception.InvalidSessionException;
import br.com.rogerroth.vote_manager.config.exception.VoteDeniedException;

@SpringBootTest
public class VotingServiceImplTest {
  
  @Mock
  VotingRepository votingRepository;

  @Mock
  SessionService sessionService;

  @Mock
  AgendaService agendaService;

  @Mock
  ValidateAssociatedRestClient validateAssociatedRestClient;

  VotingServiceImpl votingServiceImpl;

  ReceiveVoteDTO receiveVoteDTO;

  @BeforeEach
  void beforeAll() {
    receiveVoteDTO = ReceiveVoteDTO.builder()
      .id(1)
      .cpfAssociated("13231762090")
      .idAgenda(1)
      .vote(Boolean.TRUE)
      .build();
    votingServiceImpl = new VotingServiceImpl(votingRepository, sessionService, agendaService, validateAssociatedRestClient);
  }

  @Test
  public void mustBlockDuplicationOfVoteByMemberForSameAgenda() {
    when(this.sessionService.findOpeningSession(any(Integer.class)))
      .thenReturn(SessionDTO.builder().id(1).build());

    when(this.votingRepository.findByIdSessionAndCpfAssociated(any(Integer.class), any(String.class)))
      .thenReturn(Optional.of(VotingModel.builder().id(1).build()));

    assertThrows(VoteDeniedException.class, 
      () -> this.votingServiceImpl.addVote(receiveVoteDTO));
  }

  @Test
  public void mustBlockVoteConsultationForOpenSession() {
    SessionDTO sessionDTO = SessionDTO.builder()
      .id(1)
      .endAt(LocalDateTime.now().plusMinutes(50))
      .build();

    when(this.sessionService.findSession(any(Integer.class)))
      .thenReturn(sessionDTO);

    assertThrows(InvalidSessionException.class, 
      () -> this.votingServiceImpl.findResultVoting(any(Integer.class)));
  }
}
