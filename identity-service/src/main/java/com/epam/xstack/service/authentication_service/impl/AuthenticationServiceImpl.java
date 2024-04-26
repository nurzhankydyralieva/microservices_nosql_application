package com.epam.xstack.service.authentication_service.impl;


import com.epam.xstack.jwt_config.JwtService;
import com.epam.xstack.model.dto.authentication_dto.AuthenticationRequestDTO;
import com.epam.xstack.model.dto.authentication_dto.AuthenticationResponseDTO;
import com.epam.xstack.model.dto.authentication_dto.TrainerRegistrationRequestDTO;
import com.epam.xstack.model.dto.authentication_dto.TrainerRegistrationResponseDTO;
import com.epam.xstack.model.entity.User;
import com.epam.xstack.model.enums.Role;
import com.epam.xstack.repository.UserRepository;
import com.epam.xstack.service.authentication_service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getPassword()
                ));


        User traineeInDb = userRepository.findByUserName(request.getUserName()).orElseThrow();
        String jwtToken = jwtService.generateToken(traineeInDb);
        return AuthenticationResponseDTO
                .builder()
                .token(jwtToken)
                .data("You are authenticated as user with username: " + traineeInDb.getUsername())
                .build();
    }

    @Override
    public TrainerRegistrationResponseDTO saveTrainer(TrainerRegistrationRequestDTO request) {
        User createTrainer = new User();
        createTrainer.setUserName(request.getFirstName() + "." + request.getLastName());
        createTrainer.setFirstName(request.getFirstName());
        createTrainer.setLastName(request.getLastName());
        createTrainer.setPassword(passwordEncoder.encode(request.getPassword()));
        createTrainer.setIsActive(true);
        createTrainer.setRole(request.getRole());

        userRepository.save(createTrainer);
         String jwtToken = jwtService.generateToken(createTrainer);
        return TrainerRegistrationResponseDTO
                .builder()
                .userName(createTrainer.getUsername())
                .token(jwtToken)
                .password(request.getPassword())
                .build();
    }
}
