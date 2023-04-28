package com.example.emt_lab.api;

import com.example.emt_lab.models.Book;
import com.example.emt_lab.models.dto.BookDto;
import com.example.emt_lab.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll(){
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        return bookService.findById(id).map(book -> ResponseEntity.ok().body(book))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/pagination")
    public Page<Book> findAllPageable(Pageable pageable){
        return bookService.findAllPageable(pageable);
    }

    @PostMapping("/add")
    public ResponseEntity<BookDto> create(@RequestBody BookDto bookDto){
        return bookService.save(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElse(ResponseEntity.badRequest().build());
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<BookDto> update(@PathVariable Long id, @RequestBody BookDto bookDto){
        return bookService.update(id, bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteById(@PathVariable Long id){
        return bookService.delete(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<BookDto> decrementCopies(@PathVariable Long id){
        return bookService.decrementCopies(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElse(ResponseEntity.badRequest().build());
    }
}
