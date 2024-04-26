package com.epam.xstack.service.impl;

import com.epam.xstack.mapper.TrainerMapper;
import com.epam.xstack.model.dto.TrainerDTO;
import com.epam.xstack.model.entity.Trainer;
import com.epam.xstack.repository.TrainerRepository;
import com.epam.xstack.service.TrainingService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class TrainingServiceImplTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TrainerMapper trainerMapper;
    @Autowired
    private TrainingService trainingService;
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
        TrainerDTO trainerRequest = getTrainerRequest();
        String value = objectMapper.writeValueAsString(trainerRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/trainer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(value))
                .andExpect(status().isCreated());
        assertEquals(1, trainerRepository.findAll().size());
    }

    private TrainerDTO getTrainerRequest() {
        return TrainerDTO.builder()
                .userName("NewUser")
                .firstName("UserName")
                .lastName("UserLastName")
                .status("active")
                .build();
    }

    @Test
    void testShouldSearchTrainerByUserName() {
        String userName = "testUser";
        Trainer trainer = new Trainer();
        trainer.setUserName(userName);
        when(trainerRepository.findByUserName(userName)).thenReturn(trainer);

        TrainerDTO expectedDto = new TrainerDTO();
        when(trainerMapper.toDto(trainer)).thenReturn(expectedDto);

        TrainerDTO resultDto =  trainingService.searchTrainerByUserName(userName);
        assertEquals(expectedDto, resultDto);
    }
}