package com.epam.xstack.service.impl;

import com.epam.xstack.model.dto.TrainerRequestDTO;
import com.epam.xstack.model.dto.TrainerResponseDTO;
import com.epam.xstack.model.dto.TrainerResponseOkDTO;
import com.epam.xstack.model.entity.Trainer;
import com.epam.xstack.model.enums.Role;
import com.epam.xstack.repository.TrainerRepository;
import com.epam.xstack.service.TrainerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {
    private final TrainerRepository trainerRepository;
    private final WebClient.Builder webClientBuilder;
    private static final String TRAINING_MICROSERVICE_URL = "http://localhost:8083/api/training";

    @Override
    public TrainerResponseOkDTO createTrainer(TrainerRequestDTO dto) {
        Trainer trainer = Trainer.builder()
                .userName(dto.getUserName())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .password(dto.getPassword())
                .isActive(dto.getIsActive())
                .role(Role.TRAINER)
                .trainingDate(dto.getTrainingDate())
                .trainingDuration(dto.getTrainingDuration())
                .years(dto.getYears())
                .months(dto.getMonths())
                .build();
        trainerRepository.save(trainer);
        log.info("Trainer {} is saved", trainer.getUserName());
        return TrainerResponseOkDTO.builder()
                .response("Http status is: 200")
                .build();
    }

    @Override
    public List<TrainerResponseDTO> getAllTrainers() {
        List<Trainer> trainers = trainerRepository.findAll();
        log.info("Trainers {} are found", trainers);
        return trainers.stream().map(trainer -> mapToTrainerResponse(trainer)).toList();

    }

    private TrainerResponseDTO mapToTrainerResponse(Trainer trainer) {
        return TrainerResponseDTO.builder()
                .id(trainer.getId())
                .userName(trainer.getUserName())
                .lastName(trainer.getLastName())
                .firstName(trainer.getFirstName())
                .isActive(trainer.getIsActive())
                .trainingDate(trainer.getTrainingDate())
                .trainingDuration(trainer.getTrainingDuration())
                .years(trainer.getYears())
                .months(trainer.getMonths())
                .build();
    }

    @Override
    public TrainerResponseOkDTO deleteTrainer(String id) {
        Trainer trainerId = trainerRepository.findById(id).get();
        trainerRepository.delete(trainerId);
        log.info("Trainer {} is deleted", trainerId.getUserName());
        return TrainerResponseOkDTO.builder()
                .response("Trainer " + trainerId.getUserName() + " is deleted")
                .build();
    }

    @Override
    public TrainerResponseDTO updateTrainerWorkload(TrainerRequestDTO request) {
        webClientBuilder.build()
                .post()
                .uri(TRAINING_MICROSERVICE_URL)
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
        log.info("Trainer {} is updated", request.getUserName());
        return TrainerResponseDTO.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .userName(request.getUserName())
                .isActive(request.getIsActive())
                .trainingDate(request.getTrainingDate())
                .trainingDuration(request.getTrainingDuration())
                .lastName(request.getLastName())
                .years(request.getYears())
                .months(request.getMonths())
                .build();
    }
}
