package br.com.rogerroth.vote_manager.api.agenda;

public class AgendaConverter {
  
  public static AgendaDTO converterEntityToDTO(AgendaModel entity) {
    return AgendaDTO.builder()
            .id(entity.getId())
            .description((entity.getDescription()))
            .build();
  }

  public static AgendaModel converterDTOToEntity(AgendaDTO dto) {
    return AgendaModel.builder()
            .id(dto.getId())
            .description((dto.getDescription()))
            .build();
  }
}
