package com.epam.xstack.model.entity;

import com.epam.xstack.model.enums.ActionType;
import com.epam.xstack.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(value = "trainer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Trainer {
    @Id
    private String id;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private Boolean isActive;
    private Date trainingDate;
    private Number trainingDuration;
    private ActionType actionType;
    private List<Integer> years;
    private List<String> months;
    private Role role;
}
