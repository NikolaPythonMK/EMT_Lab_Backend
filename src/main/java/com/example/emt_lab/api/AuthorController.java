package com.example.emt_lab.api;

import com.example.emt_lab.models.Author;
import com.example.emt_lab.models.dto.AuthorDto;
import com.example.emt_lab.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> findAll(){
        return authorService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<AuthorDto> create(@RequestBody AuthorDto authorDto){
        return authorService.save(authorDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<AuthorDto> update(@PathVariable Long id, @RequestBody AuthorDto authorDto){
        return authorService.update(id, authorDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Author> delete(@PathVariable Long id){
        return authorService.delete(id)
                .map(author -> ResponseEntity.ok().body(author))
                .orElse(ResponseEntity.badRequest().build());
    }
}
