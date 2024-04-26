package com.epam.xstack.model.dto;

import com.epam.xstack.model.enums.ActionType;
import com.epam.xstack.model.enums.Role;
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
public class TrainerRequestDTO {
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private Boolean isActive;
    private Date trainingDate;
    private Number trainingDuration;
    private ActionType actionType;
    private Role role;
    private List<Integer> years;
    private List<String> months;
}



