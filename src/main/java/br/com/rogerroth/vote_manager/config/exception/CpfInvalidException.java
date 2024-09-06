package br.com.rogerroth.vote_manager.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CpfInvalidException extends RuntimeException {
  public CpfInvalidException(String message) {
    super(message);
  }
}
