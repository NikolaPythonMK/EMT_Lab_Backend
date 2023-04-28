package com.example.emt_lab.dataInitialize;

import com.example.emt_lab.models.Author;
import com.example.emt_lab.models.Book;
import com.example.emt_lab.models.Country;
import com.example.emt_lab.models.enumerations.Category;
import com.example.emt_lab.repository.CountryRepository;
import com.example.emt_lab.service.AuthorService;
import com.example.emt_lab.service.BookService;
import com.example.emt_lab.service.CountryService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitialize {
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;

    private static List<Country> countries = new ArrayList<>();
    private static List<Author> authors = new ArrayList<>();
    private static List<Book> books = new ArrayList<>();


    @PostConstruct
    public void init(){
        initCountries();
        initAuthors();
        initBooks();
    }

    private void initCountries(){
        countries.add(countryRepository.save(new Country("Macedonia", "Europe")));
        countries.add(countryRepository.save(new Country("Bulgaria", "Europe")));
    }

    private void initAuthors(){
        authors.add(authorService.save("Jack", "Sparrow", countries.get(0).getId()).orElse(null));
        authors.add(authorService.save("Jon", "Jones", countries.get(1).getId()).orElse(null));
        authors.add(authorService.save("Jayne", "Brooks", countries.get(0).getId()).orElse(null));
        authors.add(authorService.save("Rihanna", "Lee", countries.get(1).getId()).orElse(null));
    }

    private void initBooks(){
        books.add(bookService.save("Last Goodbye", Category.DRAMA, authors.get(0).getId(), 100).orElse(null));
        books.add(bookService.save("Who?", Category.THRILLER, authors.get(0).getId(), 100).orElse(null));
        books.add(bookService.save("Desert Rose", Category.CLASSICS, authors.get(2).getId(), 100).orElse(null));
        books.add(bookService.save("Perfume", Category.THRILLER, authors.get(1).getId(), 100).orElse(null));
        books.add(bookService.save("Me Before You", Category.DRAMA, authors.get(0).getId(), 100).orElse(null));
        books.add(bookService.save("Gladiator", Category.HISTORY, authors.get(3).getId(), 100).orElse(null));
    }
}
