package com.epam.xstack.service;

import com.epam.xstack.model.dto.TrainerRequestDTO;
import com.epam.xstack.model.dto.TrainerResponseDTO;
import com.epam.xstack.model.dto.TrainerResponseOkDTO;

import java.util.List;

public interface TrainerService {
    TrainerResponseOkDTO createTrainer(TrainerRequestDTO dto);

    List<TrainerResponseDTO> getAllTrainers();

    TrainerResponseOkDTO deleteTrainer(String id);

    TrainerResponseDTO updateTrainerWorkload(TrainerRequestDTO request);
}
