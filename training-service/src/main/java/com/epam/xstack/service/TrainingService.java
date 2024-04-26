package com.epam.xstack.service;

import com.epam.xstack.model.dto.TrainerDTO;
import com.epam.xstack.model.dto.TrainingDTO;

public interface TrainingService {
    TrainerDTO addTrainer(TrainerDTO trainerDTO);

    void updateOrSaveTrainingData(TrainingDTO trainingDTO);

    TrainerDTO searchTrainerByUserName(String userName);
    TrainerDTO searchTrainerByFirstName(String firstName);
    TrainerDTO searchTrainerByLastName(String lastName);

    TrainerDTO updateTrainer(String userName, TrainerDTO trainerDTO);
}
