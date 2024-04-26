package com.epam.xstack;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@OpenAPIDefinition(info = @Info(title = "GYM API", version = "1.0", description = "This is a sample API for Spring Microservices application for EPAM's xStack program."))
@SpringBootApplication
public class TrainerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrainerServiceApplication.class, args);
    }

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}