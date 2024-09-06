package br.com.rogerroth.vote_manager.api.agenda;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@RestController
@RequestMapping("/api/v1/agenda")
@Tag(name = "Agenda", description = "Service for agenda management")
public class AgendaController {
  
  private final AgendaService agendaService;

  @Autowired
  public AgendaController(AgendaService agendaService){
    this.agendaService = agendaService;
  }

  @Operation(summary = "Create Agenda")
  @PostMapping
  AgendaDTO createAgenda(@Valid @RequestBody AgendaDTO agendaDTO) {
    log.info("Creating agenda: {}", agendaDTO.getDescription());
    
    return agendaService.createAgenda(agendaDTO);
  }
  
}
