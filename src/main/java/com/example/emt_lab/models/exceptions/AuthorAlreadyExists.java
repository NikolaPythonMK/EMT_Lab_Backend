package com.example.emt_lab.models.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AuthorAlreadyExists extends RuntimeException{
    public AuthorAlreadyExists(Long id) {
        super(String.format("Author with id: %d already exists", id));
    }
}
