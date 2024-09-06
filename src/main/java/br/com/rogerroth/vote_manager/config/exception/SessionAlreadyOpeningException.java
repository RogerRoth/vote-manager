package br.com.rogerroth.vote_manager.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class SessionAlreadyOpeningException extends RuntimeException {
  public SessionAlreadyOpeningException(String message) {
    super(message);
  }
}
