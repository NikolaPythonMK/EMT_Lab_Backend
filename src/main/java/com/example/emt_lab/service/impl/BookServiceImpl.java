package com.example.emt_lab.service.impl;

import com.example.emt_lab.models.Author;
import com.example.emt_lab.models.Book;
import com.example.emt_lab.models.dto.BookDto;
import com.example.emt_lab.models.enumerations.Category;
import com.example.emt_lab.models.exceptions.AuthorNotFoundException;
import com.example.emt_lab.models.exceptions.BookNotFoundException;
import com.example.emt_lab.repository.AuthorRepository;
import com.example.emt_lab.repository.BookRepository;
import com.example.emt_lab.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Page<Book> findAllPageable(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> save(String name, Category category, Long authorId, int availableCopies) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        Book book = new Book(name, category, author, availableCopies);
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<BookDto> save(BookDto bookDto){
        Author author = authorRepository.findById(bookDto.getAuthorId()).orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthorId()));
        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies());
        bookRepository.save(book);
        return Optional.of(bookDto);
    }

    @Override
    public Optional<Book> update(Long id, String name, Category category, Long authorId, int availableCopies) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<BookDto> update(Long id, BookDto bookDto){
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        if(!authorRepository.existsById(bookDto.getAuthorId())){
            throw new AuthorNotFoundException(bookDto.getAuthorId());
        }
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(authorRepository.getReferenceById(bookDto.getAuthorId()));
        book.setAvailableCopies(bookDto.getAvailableCopies());
        bookRepository.save(book);
        return Optional.of(new BookDto(book.getName(), book.getCategory(), book.getAuthor().getId(), book.getAvailableCopies()));
    }

    @Override
    public Optional<BookDto> decrementCopies(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        if(book.getAvailableCopies() > 0){
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            bookRepository.save(book);
            return Optional.of(new BookDto(book.getName(), book.getCategory(), book.getAuthor().getId(), book.getAvailableCopies()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Book> delete(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isEmpty()){
            throw new BookNotFoundException(id);
        }
        bookRepository.delete(book.get());
        return book;
    }
}
