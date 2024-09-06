package br.com.rogerroth.vote_manager.api.session;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionDTO {
  
  private Integer id;
  private Integer idAgenda;
  private LocalDateTime startAt;
  private LocalDateTime endAt;
}
