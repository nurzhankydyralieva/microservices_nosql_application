package com.epam.xstack;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(info = @Info(title = "GYM API", version = "1.0", description = "This is a sample API for Spring Microservices application for EPAM's xStack program."))
@SpringBootApplication
public class TrainingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrainingServiceApplication.class, args);
    }
}