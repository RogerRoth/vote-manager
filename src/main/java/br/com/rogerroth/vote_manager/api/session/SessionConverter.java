package br.com.rogerroth.vote_manager.api.session;


public class SessionConverter {
  
  public static SessionDTO converterEntityToDTO(SessionModel entity) {
    return SessionDTO.builder()
            .id(entity.getId())
            .idAgenda(entity.getIdAgenda())
            .startAt(entity.getStartAt())
            .endAt(entity.getEndAt())
            .build();
  }
}
