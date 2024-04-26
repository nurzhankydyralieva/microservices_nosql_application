package com.epam.xstack.service.authentication_service;


import com.epam.xstack.model.dto.authentication_dto.AuthenticationRequestDTO;
import com.epam.xstack.model.dto.authentication_dto.AuthenticationResponseDTO;
import com.epam.xstack.model.dto.authentication_dto.TrainerRegistrationRequestDTO;
import com.epam.xstack.model.dto.authentication_dto.TrainerRegistrationResponseDTO;

public interface AuthenticationService {

    AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request);
    TrainerRegistrationResponseDTO saveTrainer(TrainerRegistrationRequestDTO request);
}
