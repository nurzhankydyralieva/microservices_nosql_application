package com.epam.xstack.exceptions.exception;

import com.epam.xstack.exceptions.error_models.Error;
import com.epam.xstack.exceptions.error_models.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNameOrPasswordNotCorrectException.class)
    public ResponseEntity<ErrorResponse> handleUserNotCorrectException(UserNameOrPasswordNotCorrectException ex) {
        log.info("User name or password not correct -  Exception: {}", ex.toString());
        return new ResponseEntity<>(ErrorResponse.builder().error(Error.builder()
                .message(ex.getMessage())
                .build()).build(), ex.getHttpStatus());
    }
}