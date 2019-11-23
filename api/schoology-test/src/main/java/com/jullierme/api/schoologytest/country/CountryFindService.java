package com.jullierme.api.schoologytest.country;

import com.jullierme.api.schoologytest.domain.Country;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CountryFindService {
    Optional<List<Country>> findByName(String name, Pageable pageable);

    default Optional<List<Country>> findByName(String name) {
        return findByName(name, null);
    }
}
