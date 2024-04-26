package com.epam.xstack.exceptions.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Slf4j
@Service
public class NotNullValidation {

    public void nullValidation(BindingResult result) {
        if (result.hasErrors()) {
            result.getFieldErrors()
                    .forEach(f -> System.out.println(f.getField() + ": " + f.getDefaultMessage()));
        }
    }

    public void userNotNullValidation(BindingResult result) {
        if (result.hasErrors()) {
            result.getFieldErrors()
                    .forEach(f -> System.out.println(f.getField() + ": " + f.getDefaultMessage()));
        }
    }
}
