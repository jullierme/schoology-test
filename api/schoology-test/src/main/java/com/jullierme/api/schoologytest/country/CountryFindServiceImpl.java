package com.jullierme.api.schoologytest.country;

import com.jullierme.api.schoologytest.domain.Country;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CountryFindServiceImpl implements CountryFindService {
    private CountryFindRepository countryFindRepository;

    @Override
    public Optional<List<Country>> findByName(String name, Pageable pageable) {
        return countryFindRepository.findByName(name, pageable);
    }
}
