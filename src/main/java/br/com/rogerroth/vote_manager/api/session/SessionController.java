package br.com.rogerroth.vote_manager.api.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@RestController
@RequestMapping("/api/v1/session")
@Tag(name = "Session", description = "Service for session management of voting")
public class SessionController {
  
  private final SessionService sessionService;

  @Autowired
  public SessionController(final SessionService sessionService){
    this.sessionService = sessionService;
  }

  @Operation(summary = "Create sission for an agenda")
  @PostMapping
  public SessionDTO openingSession(@Valid @RequestBody SessionOpeningDTO sessionOpeningDTO) {
      log.info("There is a request to open the following agenda: {}", sessionOpeningDTO.getIdAgenda());
      
      return this.sessionService.openSession(sessionOpeningDTO);
  }
  
}
