package com.example.emt_lab.service;

import com.example.emt_lab.models.Author;
import com.example.emt_lab.models.Book;
import com.example.emt_lab.models.dto.BookDto;
import com.example.emt_lab.models.enumerations.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Page<Book> findAllPageable(Pageable pageable);

    Optional<Book> save(String name, Category category, Long authorId, int availableCopies);

    Optional<BookDto> save(BookDto bookDto);

    Optional<Book> delete(Long id);

    Optional<Book> update(Long id, String name, Category category, Long authorId, int availableCopies);

    Optional<BookDto> update(Long id, BookDto bookDto);

    Optional<BookDto> decrementCopies(Long id);
}
