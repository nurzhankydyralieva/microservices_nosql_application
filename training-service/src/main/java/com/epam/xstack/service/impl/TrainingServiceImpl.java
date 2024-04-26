package com.epam.xstack.service.impl;

import com.epam.xstack.exceptions.UserAlreadyExistsException;
import com.epam.xstack.mapper.TrainerMapper;
import com.epam.xstack.mapper.TrainingMapper;
import com.epam.xstack.model.dto.TrainerDTO;
import com.epam.xstack.model.dto.TrainingDTO;
import com.epam.xstack.model.entity.Trainer;
import com.epam.xstack.model.entity.Training;
import com.epam.xstack.repository.TrainerRepository;
import com.epam.xstack.repository.TrainingRepository;
import com.epam.xstack.service.TrainingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class TrainingServiceImpl implements TrainingService {
    private final TrainingRepository trainingRepository;
    private final TrainerRepository trainerRepository;
    private final TrainingMapper trainingMapper;
    private final TrainerMapper trainerMapper;

    @Override
    public TrainerDTO addTrainer(TrainerDTO trainerDTO) {
        Trainer newTrainer = trainerMapper.toEntity(trainerDTO);
        Trainer userNameInDB = trainerRepository.findByUserName(newTrainer.getUserName());

        if (userNameInDB != null) {
            throw UserAlreadyExistsException.builder()
                    .message("User name already exists")
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
        } else {
            Trainer trainer = Trainer.builder()
                    .firstName(newTrainer.getFirstName())
                    .lastName(newTrainer.getLastName())
                    .userName(newTrainer.getUserName())
                    .status(newTrainer.getStatus())
                    .years(newTrainer.getYears())
                    .build();
            trainerRepository.save(trainer);
        }
        log.info("New trainer created");
        return TrainerDTO.builder()
                .trainings(trainerDTO.getTrainings())
                .months(trainerDTO.getMonths())
                .years(trainerDTO.getYears())
                .duration(trainerDTO.getDuration())
                .firstName(trainerDTO.getFirstName())
                .lastName(trainerDTO.getLastName())
                .userName(trainerDTO.getUserName())
                .status(trainerDTO.getStatus())
                .build();
    }

    @Override
    public TrainerDTO searchTrainerByUserName(String userName) {
        Trainer byUserName = trainerRepository.findByUserName(userName);

        if (byUserName != null) {
            return trainerMapper.toDto(byUserName);
        } else {
            log.info("No trainer found for username {}", userName);
        }
        return TrainerDTO.builder().build();
    }

    @Override
    public TrainerDTO searchTrainerByFirstName(String firstName) {
        Trainer firstNameInDb = trainerRepository.findByFirstName(firstName);

        if (firstNameInDb != null) {
            return trainerMapper.toDto(firstNameInDb);
        } else {
            log.info("No trainer found for firstName {}", firstName);
        }
        return TrainerDTO.builder().build();
    }

    @Override
    public TrainerDTO searchTrainerByLastName(String lastName) {
        Trainer lastNameInDb = trainerRepository.findByLastName(lastName);

        if (lastNameInDb != null) {
            return trainerMapper.toDto(lastNameInDb);
        } else {
            log.info("No trainer found for lastName {}", lastName);
        }
        return TrainerDTO.builder().build();
    }


    @Override
    public TrainerDTO updateTrainer(String userName, TrainerDTO trainerDTO) {
        Trainer trainer = trainerMapper.toEntity(trainerDTO);
        Trainer trainerUserName = trainerRepository.findByUserName(userName);

        if (trainerUserName != null) {
            trainerUserName.setFirstName(trainerDTO.getFirstName());
            trainerUserName.setLastName(trainerDTO.getLastName());
            trainerUserName.setUserName(trainerDTO.getUserName());
            trainerUserName.setStatus(trainerDTO.getStatus());
            trainerUserName.setYears(trainerDTO.getYears());
            trainerUserName.setMonths(trainerDTO.getMonths());
            trainerUserName.setDuration(trainerDTO.getDuration());
            trainerUserName.setTrainings(trainer.getTrainings());

            trainerRepository.save(trainerUserName);
        }
        log.info("Trainer is updated");
        return TrainerDTO.builder()
                .userName(trainerUserName.getUserName())
                .firstName(trainerUserName.getFirstName())
                .lastName(trainerUserName.getLastName())
                .duration(trainerUserName.getDuration())
                .months(trainerUserName.getMonths())
                .years(trainerUserName.getYears())
                .status(trainerUserName.getStatus())
                .build();
    }

    @Override
    public void updateOrSaveTrainingData(TrainingDTO trainingDTO) {
        Training trainingMapperEntity = trainingMapper.toEntity(trainingDTO);
        Training trainingById = trainingRepository.findTrainingById(trainingMapperEntity.getId());

        if (trainingById != null) {
            trainingById.setDuration(trainingDTO.getDuration());
            trainingById.setTrainer(trainingMapperEntity.getTrainer());
            trainingById.setYear(trainingDTO.getYear());
            trainingById.setMonth(trainingDTO.getMonth());

            trainingRepository.save(trainingById);
            log.info("Training is updated");
        } else {
            Integer year = extractYear(trainingDTO.getYear());
            Integer month = extractMonth(trainingDTO.getMonth());
            trainingById = new Training(year, month, trainingDTO.getDuration());

            trainingRepository.save(trainingById);
            log.info("New training created");
        }
    }

    private Integer extractYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    private Integer extractMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }
}
