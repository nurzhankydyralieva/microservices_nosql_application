package com.epam.xstack.model.dto;

import com.epam.xstack.model.entity.Trainer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainingDTO {
    String id;
    Date month;
    Date year;
    Number duration;
    TrainerDTO trainer;
}
