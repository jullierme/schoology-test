package com.jullierme.api.schoologytest.country.mapper;

import com.jullierme.api.schoologytest.country.response.CountryResponse;
import com.jullierme.api.schoologytest.domain.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapperImpl implements CountryMapper {
    @Override
    public CountryResponse toResponse(Country country) {
        return CountryResponse.builder()
                .name(country.getName())
                .abbreviation(country.getAbbreviation())
                .build();
    }
}
