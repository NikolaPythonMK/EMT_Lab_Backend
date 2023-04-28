package com.example.emt_lab.service;

import com.example.emt_lab.models.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> findAll();

    Optional<Country> findByName(String name);

    Optional<Country> save(String name, String continent);

    Optional<Country> update(Long id, String name, String continent);

    Optional<Country> delete(Long id);
}
