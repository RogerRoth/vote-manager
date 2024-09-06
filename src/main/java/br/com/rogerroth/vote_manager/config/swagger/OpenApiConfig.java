package br.com.rogerroth.vote_manager.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
  
  @Bean
  public OpenAPI customOpenAPI(){
    return new OpenAPI()
            .components(new Components())
            .info(new Info()
                    .title("Voting manager API")
                    .description("Service to manage voting.")
                    .contact(
                        new Contact()
                              .name("Roger Rothmund")
                              .email("roger.rothmund@gmail.com")
                    )
                    .version("1.0.0")
                );
  }
}
