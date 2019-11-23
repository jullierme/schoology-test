package com.jullierme.api.schoologytest.country.controller;

import com.jullierme.api.schoologytest.country.response.CountryResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CountryApiService {
    List<CountryResponse> findByName(String name, Pageable pageable);

    default List<CountryResponse> findByName(String name) {
        return findByName(name, null);
    }
}
