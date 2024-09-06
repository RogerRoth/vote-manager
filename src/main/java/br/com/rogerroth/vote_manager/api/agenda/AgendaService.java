package br.com.rogerroth.vote_manager.api.agenda;

public interface AgendaService {
  
  public AgendaDTO createAgenda(AgendaDTO agendaDTO);

  public AgendaDTO findAgenda(Integer id);

}
