package br.com.rogerroth.vote_manager.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import br.com.rogerroth.vote_manager.config.exception.CpfInvalidException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ValidateAssociatedRestClientImpl implements ValidateAssociatedRestClient {

  private static final String URL = "https://user-info.herokuapp/users/{cpf}";
  private final String CAN_VOTE = "ABLE_TO_VOTE";
  
  @Autowired
  public ValidateAssociatedRestClientImpl(){}

  @Override
  public Boolean validateAssociated(String cpf) throws CpfInvalidException {
    try {
      RestTemplate rest = new RestTemplate();
      ResponseEntity<String> response = rest.getForEntity(URL, String.class, cpf);

      log.info("Validating if associated can vote");

      if (response.getStatusCode().equals(HttpStatus.OK)){
        Gson gson = new Gson();
        ValidateAssociatedDTO validateAssociatedDTO = gson.fromJson(response.getBody(), ValidateAssociatedDTO.class);
        return validateAssociatedDTO.getStatus().equals(CAN_VOTE);
      } else {
        log.error("Error during associated validation");

        if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
          throw new CpfInvalidException("Invalid CPF: " + cpf);
        }

        log.error("Return: {}", response.getStatusCode());
        return Boolean.FALSE;
      }
    } catch (CpfInvalidException err) {
      throw err;
    } catch (JsonSyntaxException | RestClientException err) {
      log.error("Error searching associated");
      return Boolean.FALSE;
    }
  }
  
}
