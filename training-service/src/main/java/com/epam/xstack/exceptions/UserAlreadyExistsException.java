package com.epam.xstack.exceptions;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAlreadyExistsException extends RuntimeException {

    private final String message;
    private final HttpStatus httpStatus;

}
