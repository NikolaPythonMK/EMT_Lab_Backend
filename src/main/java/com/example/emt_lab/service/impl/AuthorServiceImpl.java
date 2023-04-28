package com.example.emt_lab.service.impl;

import com.example.emt_lab.models.Author;
import com.example.emt_lab.models.Country;
import com.example.emt_lab.models.dto.AuthorDto;
import com.example.emt_lab.models.exceptions.AuthorNotFoundException;
import com.example.emt_lab.models.exceptions.CountryNotFoundException;
import com.example.emt_lab.repository.AuthorRepository;
import com.example.emt_lab.repository.CountryRepository;
import com.example.emt_lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> save(String name, String surname, Long countryId) {
        Country country = countryRepository.findById(countryId).orElseThrow(() -> new CountryNotFoundException(countryId));
        Author author = new Author(name, surname, country);
        return Optional.of(authorRepository.save(author));
    }

    @Override
    public Optional<AuthorDto> save(AuthorDto authorDto){
        Country country = countryRepository.findByName(authorDto.getCountry().getName())
                .orElseThrow(() -> new CountryNotFoundException(authorDto.getCountry().getName()));
        Author author = new Author(authorDto.getName(), authorDto.getSurname(), country);
        authorRepository.save(author);
        return Optional.of(authorDto);
    }

    @Override
    public Optional<Author> update(Long id, String name, String surname, Long countryId) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        Country country = countryRepository.findById(countryId).orElseThrow(() -> new CountryNotFoundException(countryId));
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        return Optional.of(author);
    }

    @Override
    public Optional<AuthorDto> update(Long id, AuthorDto authorDto){
        Author author = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        Country country = countryRepository.findByName(authorDto.getCountry().getName())
                .orElseThrow(() -> new CountryNotFoundException(authorDto.getCountry().getId()));
        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        author.setCountry(country);
        authorRepository.save(author);
        return Optional.of(new AuthorDto(author.getName(), author.getSurname(), author.getCountry()));
    }

    @Override
    public Optional<Author> delete(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if(author.isEmpty()){
            throw new AuthorNotFoundException(id);
        }
        authorRepository.delete(author.get());
        return author;
    }
}
