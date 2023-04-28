package com.example.emt_lab.api;

import com.example.emt_lab.models.Country;
import com.example.emt_lab.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> findAll(){
        return countryService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Country> create(@RequestParam String name, @RequestParam String continent){
        return countryService.save(name, continent)
                .map(country -> ResponseEntity.ok().body(country))
                .orElse(ResponseEntity.badRequest().build());
    }
}
