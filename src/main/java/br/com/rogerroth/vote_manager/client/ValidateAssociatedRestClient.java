package br.com.rogerroth.vote_manager.client;

import br.com.rogerroth.vote_manager.config.exception.CpfInvalidException;

public interface ValidateAssociatedRestClient {

  public Boolean validateAssociated(String cpf) throws CpfInvalidException;

}
