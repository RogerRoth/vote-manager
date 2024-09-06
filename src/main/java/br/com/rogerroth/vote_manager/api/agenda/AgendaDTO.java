package br.com.rogerroth.vote_manager.api.agenda;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgendaDTO {
  private Integer id;

  @NotBlank(message = "A message must be informed")
  private String description;
}
