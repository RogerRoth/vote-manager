package br.com.rogerroth.vote_manager.api.session;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rogerroth.vote_manager.api.agenda.AgendaDTO;
import br.com.rogerroth.vote_manager.api.agenda.AgendaService;
import br.com.rogerroth.vote_manager.config.exception.RecordNotFoundException;
import br.com.rogerroth.vote_manager.config.exception.SessionAlreadyOpeningException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SessionServiceImpl implements SessionService{

  private final Integer DEFAULT_DURATION = 1;

  private final SessionRepository sessionRepository;

  private final AgendaService agendaService;

  @Autowired
  public SessionServiceImpl(final SessionRepository sessionRepository, final AgendaService agendaService){
    this.sessionRepository = sessionRepository;
    this.agendaService = agendaService;
  }

  @Override
  public SessionDTO openSession(SessionOpeningDTO sessionOpeningDTO) {
    AgendaDTO agendaDTO = this.agendaService.findAgenda(sessionOpeningDTO.getIdAgenda());

    log.info("Agenda: {} found in data base, opening voting session.", agendaDTO.getDescription());

    LocalDateTime start = LocalDateTime.now();

    Optional<SessionModel> sessionOptional = this.sessionRepository.findByOpeningSession(sessionOpeningDTO.getIdAgenda(), start);

    if(sessionOptional.isPresent()){
      log.error("There is already an open voting session for the following agenda: {}", sessionOpeningDTO.getIdAgenda());

      throw new SessionAlreadyOpeningException("There is already an open voting session for the following agenda: " + sessionOpeningDTO.getIdAgenda());
    }

    SessionModel sessionModel = SessionModel.builder()
                                  .idAgenda(agendaDTO.getId())
                                  .startAt(start)
                                  .endAt(getEndTimeSession(start, sessionOpeningDTO.getDuration()))
                                  .build();
    
    sessionModel = this.sessionRepository.save(sessionModel);

    return SessionConverter.converterEntityToDTO(sessionModel);
  }

  private LocalDateTime getEndTimeSession(LocalDateTime start, Integer duration){
    duration = Objects.isNull(duration) ? DEFAULT_DURATION : duration;
    return start.plusMinutes(duration);
  }

  @Override
  public SessionDTO findOpeningSession(Integer idAgenda) {
    Optional<SessionModel> sessionOptional = this.sessionRepository.findByOpeningSession(idAgenda, LocalDateTime.now());
    
    if (!sessionOptional.isPresent()) {
      throw new RecordNotFoundException("There is no session for the following agenda: " + idAgenda);
    }

    return SessionConverter.converterEntityToDTO(sessionOptional.get());
  }

  @Override
  public SessionDTO findSession(Integer idSession) {
    Optional<SessionModel> sessionOptional = this.sessionRepository.findById(idSession);
    
    if (!sessionOptional.isPresent()) {
      throw new RecordNotFoundException("There is no session with the following id: " + idSession);
    }

    return SessionConverter.converterEntityToDTO(sessionOptional.get());
  }


}
