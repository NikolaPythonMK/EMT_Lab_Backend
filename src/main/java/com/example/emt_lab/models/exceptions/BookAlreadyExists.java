package com.example.emt_lab.models.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BookAlreadyExists extends RuntimeException{
    public BookAlreadyExists(Long id) {
        super(String.format("Book with id: %d already exists", id));
    }
}
