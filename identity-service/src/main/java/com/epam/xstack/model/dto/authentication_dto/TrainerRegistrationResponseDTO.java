package com.epam.xstack.model.dto.authentication_dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainerRegistrationResponseDTO {
    String userName;
    String password;
    String token;
}

