package com.example.emt_lab.models.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CountryNotFoundException extends RuntimeException{
    public CountryNotFoundException(Long id) {
        super(String.format("Country with id: %d is not found", id));
    }

    public CountryNotFoundException(String message) {
        super(String.format("Country with name %s is not found", message));
    }
}
