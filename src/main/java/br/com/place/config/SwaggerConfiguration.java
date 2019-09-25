package br.com.place.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Collections;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.ant("/api/**"))
        .build()
        .apiInfo(buildApiInfo());
  }

  private ApiInfo buildApiInfo() {       
        return new ApiInfo(
          "Place REST API",                                                                 // Nome da API
          "API simple API to manage places (CRUD)",                                       // Descrição
          "1.0",                                                                            // Versão
          "",                                                                               // URL de Termos de uso
          new Contact("Glaucio Ferreira", "Cel. 21-96410-0915", "carvanny@gmail.com"),      // Contato
          "Apache License Version 2.0",                                                     // Licensa
          "https://www.apache.org/licenses/LICENSE-2.0",                                    // URL de licensa
          Collections.emptyList()                                                           // Extensão de fornecedores
        );

    }
}