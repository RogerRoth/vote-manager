package br.com.rogerroth.vote_manager.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidSessionException extends RuntimeException {
  public InvalidSessionException(String message) {
    super(message);
  }
}
