package com.epam.xstack.service.impl;

import com.epam.xstack.model.dto.TrainerResponseDTO;
import com.epam.xstack.repository.TrainerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class TrainerServiceImplTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TrainerRepository trainerRepository;
    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.4");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.data.mongodb.host", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    void shouldCreateTrainer() throws Exception {
        TrainerResponseDTO trainerRequest = getTrainerRequest();
        String value = objectMapper.writeValueAsString(trainerRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/trainer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(value))
                .andExpect(status().isCreated());
        assertEquals(5, trainerRepository.findAll().size());
    }

    private TrainerResponseDTO getTrainerRequest() {
        return TrainerResponseDTO.builder()
                .userName("AndreaBocelli")
                .firstName("Andrea")
                .lastName("Bocelli")
                .isActive(true)
                .build();
    }
}