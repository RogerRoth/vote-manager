package br.com.rogerroth.vote_manager.api.voting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/voting")
@Tag (name = "Voting", description = "Services to manage voting on agendas.")
public class VotingController {
  private final VotingService votingService;

  public VotingController(final VotingService votingService) {
      this.votingService = votingService;
  }

  @Operation ( summary = "Vote on a specific agenda.")
  @PostMapping
  VotingDTO vote(@Valid @RequestBody ReceiveVoteDTO receiveVoteDTO) {
      log.info("Attempted vote on the agenda: {}", receiveVoteDTO.getIdAgenda());
      return this.votingService.addVote(receiveVoteDTO);
  }

  @Operation ( summary = "Display voting results for a given session.")
  @GetMapping("/result/session/{idSession}")
  ResultVotingDTO buscarResultado(@PathVariable("idSession") Integer idSession) {
      log.info("Search voting results for session: {}", idSession);
      return this.votingService.findResultVoting(idSession);
  }
}
