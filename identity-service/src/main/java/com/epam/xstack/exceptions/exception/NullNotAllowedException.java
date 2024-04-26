package com.epam.xstack.exceptions.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NullNotAllowedException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;
}
