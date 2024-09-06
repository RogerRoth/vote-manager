package br.com.rogerroth.vote_manager.api.session;

public interface SessionService {
  
  public SessionDTO openSession(SessionOpeningDTO sessionOpeningDTO);

  public SessionDTO findOpeningSession(Integer idAgenda);

  public SessionDTO findSession(Integer idSession);
}
