package com.example.emt_lab.service;

import com.example.emt_lab.models.Author;
import com.example.emt_lab.models.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> save(String name, String surname, Long countryId);

    Optional<AuthorDto> save(AuthorDto authorDto);

    Optional<Author> update(Long id, String name, String surname, Long countryId);

    Optional<AuthorDto> update(Long id, AuthorDto authorDto);

    Optional<Author> delete(Long id);
}
