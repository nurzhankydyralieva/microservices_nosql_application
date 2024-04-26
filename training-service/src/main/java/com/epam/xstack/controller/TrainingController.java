package com.epam.xstack.controller;

import com.epam.xstack.model.dto.TrainerDTO;
import com.epam.xstack.model.dto.TrainingDTO;
import com.epam.xstack.service.TrainingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TrainingController {
    private final TrainingService trainerService;

    @Operation(summary = "Save Training to database",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User saved successfully"),
                    @ApiResponse(responseCode = "401", description = "Bad credentials"),
                    @ApiResponse(responseCode = "422", description = "User or password is null")})
    @PostMapping("/training")
    public void saveTrainingData(@RequestBody TrainingDTO trainingDTO) {
        log.info("Saving training data");
        trainerService.updateOrSaveTrainingData(trainingDTO);
    }

    @Operation(summary = "Save Training to database",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User saved successfully"),
                    @ApiResponse(responseCode = "401", description = "Bad credentials"),
                    @ApiResponse(responseCode = "422", description = "User or password is null")})
    @PostMapping("/trainer")
    public ResponseEntity<TrainerDTO> creatTrainer(@RequestBody TrainerDTO trainerDTO) {
        log.info("Saving trainer data");
        return new ResponseEntity<>(trainerService.addTrainer(trainerDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Get Trainer by userName",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User selected successfully"),
                    @ApiResponse(responseCode = "401", description = "Bad credentials"),
                    @ApiResponse(responseCode = "422", description = "User or password is null")})
    @GetMapping("/trainer/{userName}")
    public TrainerDTO getTrainerRecordByUserName(@PathVariable("userName") String userName) {
        log.info("Get Trainer record by UserName");
        return trainerService.searchTrainerByUserName(userName);
    }

    @Operation(summary = "Get Trainer by firstName",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User selected successfully"),
                    @ApiResponse(responseCode = "401", description = "Bad credentials"),
                    @ApiResponse(responseCode = "422", description = "User or password is null")})
    @GetMapping("/trainer/first-name/{firstName}")
    public TrainerDTO getTrainerRecordByFirstName(@PathVariable("firstName") String firstName) {
        log.info("Get Trainer record by firstName");
        return trainerService.searchTrainerByFirstName(firstName);
    }

    @Operation(summary = "Get Trainer by firstName",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User selected successfully"),
                    @ApiResponse(responseCode = "401", description = "Bad credentials"),
                    @ApiResponse(responseCode = "422", description = "User or password is null")})
    @GetMapping("/trainer/trainer-last-name/{lastName}")
    public TrainerDTO getTrainerRecordByLastName(@PathVariable("lastName") String lastName) {
        log.info("Get Trainer record by lastName");
        return trainerService.searchTrainerByLastName(lastName);
    }

    @Operation(summary = "Update Trainer by userName database",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User updated successfully"),
                    @ApiResponse(responseCode = "401", description = "Bad credentials"),
                    @ApiResponse(responseCode = "422", description = "User or password is null")})
    @PatchMapping("/trainer/{userName}")
    public TrainerDTO updateTrainer(@PathVariable("userName") String userName, @RequestBody TrainerDTO trainerDTO) {
        log.info("Update Trainer record by UserName");
        return trainerService.updateTrainer(userName, trainerDTO);
    }

}
