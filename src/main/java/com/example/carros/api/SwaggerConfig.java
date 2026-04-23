package com.example.carros.api;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Carros API")
                        .description("Documentação API dos Carros")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Gabriel Alvares")
                                .url("https://www.linkedin.com/in/gabrielcanjos/")
                                .email("gabrielalvares500@gmail.com")
                        ));
    }
}
