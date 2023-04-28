package com.example.emt_lab.service.impl;

import com.example.emt_lab.models.Author;
import com.example.emt_lab.models.Country;
import com.example.emt_lab.models.exceptions.CountryNotFoundException;
import com.example.emt_lab.repository.CountryRepository;
import com.example.emt_lab.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findByName(String name){
        return countryRepository.findByName(name);
    }

    @Override
    public Optional<Country> save(String name, String continent) {
        return Optional.of(countryRepository.save(new Country(name, continent)));
    }

    @Override
    public Optional<Country> update(Long id, String name, String continent) {
        Country country = countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));
        country.setName(name);
        country.setContinent(continent);
        return Optional.of(countryRepository.save(country));
    }

    @Override
    public Optional<Country> delete(Long id) {
        Optional<Country> country = countryRepository.findById(id);
        if(country.isEmpty()){
            throw new CountryNotFoundException(id);
        }
        countryRepository.delete(country.get());
        return country;
    }
}
