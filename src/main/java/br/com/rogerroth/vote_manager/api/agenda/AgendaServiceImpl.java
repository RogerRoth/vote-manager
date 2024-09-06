package br.com.rogerroth.vote_manager.api.agenda;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rogerroth.vote_manager.client.ValidateAssociatedRestClient;
import br.com.rogerroth.vote_manager.config.exception.RecordNotFoundException;

@Service
public class AgendaServiceImpl implements AgendaService {

  private final AgendaRepository agendaRepository;

  private final ValidateAssociatedRestClient validateAssociatedRestClient;

  @Autowired
  public AgendaServiceImpl(final AgendaRepository agendaRepository, final ValidateAssociatedRestClient validateAssociatedRestClient){
    this.agendaRepository = agendaRepository;
    this.validateAssociatedRestClient = validateAssociatedRestClient;
  }

  @Override
  public AgendaDTO createAgenda(AgendaDTO agendaDTO) {
    AgendaModel agendaModel = AgendaConverter.converterDTOToEntity(agendaDTO);
    agendaModel = this.agendaRepository.save(agendaModel);

    return AgendaConverter.converterEntityToDTO(agendaModel);
  }

  @Override
  public AgendaDTO findAgenda(Integer id) {
    Optional<AgendaModel> agendaOptional = this.agendaRepository.findById(id);

    if(!agendaOptional.isPresent()){
      throw new RecordNotFoundException("The agenda "+id+" not found.");
    }

    return AgendaConverter.converterEntityToDTO(agendaOptional.get());
  }
  
}
