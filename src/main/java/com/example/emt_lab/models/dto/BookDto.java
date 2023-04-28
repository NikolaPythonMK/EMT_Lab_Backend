package com.example.emt_lab.models.dto;

import com.example.emt_lab.models.Author;
import com.example.emt_lab.models.enumerations.Category;
import lombok.Data;

@Data
public class BookDto {

    private Long id;

    private String name;

    private Category category;

    private Long authorId;

    private int availableCopies;

    public BookDto(String name, Category category, Long authorId, int availableCopies) {
        this.name = name;
        this.category = category;
        this.authorId = authorId;
        this.availableCopies = availableCopies;
    }
}
