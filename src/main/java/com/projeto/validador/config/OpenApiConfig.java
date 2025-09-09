package com.projeto.validador.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${server.port:8080}")
    private String serverPort;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Validador Temp")
                        .description("Documentação da API Validador Temp usando SpringDoc OpenAPI 3")
                        .version("v1.0.0"))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:" + serverPort)
                                .description("Ambiente Local"),
                        new Server()
                                .url("https://validador-temp-73510068529.us-central1.run.app")
                                .description("Ambiente de Desenvolvimento"),
                        new Server()
                                .url("https://prod.com")
                                .description("Ambiente de Produção")
                ));
    }
}
