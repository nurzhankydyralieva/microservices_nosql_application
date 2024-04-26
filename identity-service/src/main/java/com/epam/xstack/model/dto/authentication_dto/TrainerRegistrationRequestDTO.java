package com.epam.xstack.model.dto.authentication_dto;

import com.epam.xstack.model.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainerRegistrationRequestDTO {
    String firstName;
    String lastName;
    String password;
    Role role;
}
