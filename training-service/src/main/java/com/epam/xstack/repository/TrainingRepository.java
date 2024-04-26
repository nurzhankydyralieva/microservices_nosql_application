package com.epam.xstack.repository;

import com.epam.xstack.model.entity.Training;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrainingRepository extends MongoRepository<Training, String> {
    Training findTrainingById(String id);
}
