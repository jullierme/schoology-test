package com.jullierme.api.schoologytest.country.controller;

import com.jullierme.api.schoologytest.country.CountryFindService;
import com.jullierme.api.schoologytest.country.mapper.CountryMapper;
import com.jullierme.api.schoologytest.country.response.CountryResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CountryApiServiceImpl implements CountryApiService {
    private CountryFindService countryFindService;
    private CountryMapper countryMapper;

    @Override
    public List<CountryResponse> findByName(String name, Pageable pageable) {
        return countryFindService.findByName(name, pageable)
                .map(list -> list.stream().map(countryMapper::toResponse)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()))
                .filter(a -> !a.isEmpty())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource"));
    }
}
