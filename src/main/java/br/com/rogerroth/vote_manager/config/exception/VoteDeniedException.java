package br.com.rogerroth.vote_manager.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class VoteDeniedException extends RuntimeException {
  public VoteDeniedException(String message) {
    super(message);
  }
}
