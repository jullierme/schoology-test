package com.jullierme.api.schoologytest.country.controller;

import com.jullierme.api.schoologytest.country.response.CountryResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("country")
@CrossOrigin(origins = "*")
public class CountryController {
    private CountryApiService countryApiService;

    @GetMapping("/find-by-name/{name}")
    public List<CountryResponse> findByName(@PathVariable String name, Pageable pageable) {
        return countryApiService.findByName(name, pageable);
    }
}
