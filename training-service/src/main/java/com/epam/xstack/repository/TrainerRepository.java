package com.epam.xstack.repository;

import com.epam.xstack.model.entity.Trainer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends MongoRepository<Trainer, String> {
    Trainer findByUserName(String userName);
    Trainer findByFirstName(String firstName);
    Trainer findByLastName(String lastName);
}
