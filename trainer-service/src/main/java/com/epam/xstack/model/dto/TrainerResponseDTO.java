package com.epam.xstack.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TrainerResponseDTO {
    private String id;
    private String userName;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private Date trainingDate;
    private Number trainingDuration;
    private List<Integer> years;
    private List<String> months;
}
