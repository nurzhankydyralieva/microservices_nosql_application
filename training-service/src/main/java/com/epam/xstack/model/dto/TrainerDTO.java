package com.epam.xstack.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainerDTO {
    String userName;
    String firstName;
    String lastName;
    String status;
    Number duration;
    List<Integer> years;
    List<String> months;
    List<TrainingDTO> trainings;
}
