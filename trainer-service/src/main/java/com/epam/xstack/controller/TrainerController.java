package com.epam.xstack.controller;

import com.epam.xstack.model.dto.TrainerRequestDTO;
import com.epam.xstack.model.dto.TrainerResponseDTO;
import com.epam.xstack.model.dto.TrainerResponseOkDTO;
import com.epam.xstack.service.TrainerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/trainer")
@RequiredArgsConstructor
public class TrainerController {
    private final TrainerService trainerService;

    @PostMapping
    public ResponseEntity<TrainerResponseOkDTO> createTrainer(@RequestBody TrainerRequestDTO trainerRequestDTO) {
        log.info("Method create in Trainer Controller is ended");
        return new ResponseEntity<>(trainerService.createTrainer(trainerRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TrainerResponseDTO> getAllTrainers() {
        log.info("Method get all Trainers in Trainer Controller is ended");
        return trainerService.getAllTrainers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TrainerResponseOkDTO> deleteTrainer(@PathVariable("id") String id) {
        log.info("Method delete in Trainer Controller is ended");
        return new ResponseEntity<>(trainerService.deleteTrainer(id), HttpStatus.OK);
    }

    @PostMapping("/workload")
    public ResponseEntity<TrainerResponseDTO> updateTrainerWorkload(@RequestBody TrainerRequestDTO request) {
        log.info("Method update in Trainer Controller is ended");
        return new ResponseEntity<>(trainerService.updateTrainerWorkload(request), HttpStatus.OK);
    }
}
