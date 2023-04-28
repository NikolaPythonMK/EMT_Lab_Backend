package com.example.emt_lab.models.dto;

import com.example.emt_lab.models.Country;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class AuthorDto {
    private String name;

    private String surname;

    @ManyToOne
    private Country country;

    public AuthorDto(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
