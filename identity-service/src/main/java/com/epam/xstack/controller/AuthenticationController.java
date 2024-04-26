package com.epam.xstack.controller;

import com.epam.xstack.exceptions.validator.NotNullValidation;
import com.epam.xstack.model.dto.authentication_dto.AuthenticationRequestDTO;
import com.epam.xstack.model.dto.authentication_dto.AuthenticationResponseDTO;
import com.epam.xstack.model.dto.authentication_dto.TrainerRegistrationRequestDTO;
import com.epam.xstack.model.dto.authentication_dto.TrainerRegistrationResponseDTO;
import com.epam.xstack.service.authentication_service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;
    private final NotNullValidation validation;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@Valid @RequestBody AuthenticationRequestDTO request, BindingResult bindingResult) {
        validation.nullValidation(bindingResult);
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<TrainerRegistrationResponseDTO> register(@Valid @RequestBody TrainerRegistrationRequestDTO request, BindingResult result) {
        validation.nullValidation(result);
        return ResponseEntity.ok(service.saveTrainer(request));
    }
}
