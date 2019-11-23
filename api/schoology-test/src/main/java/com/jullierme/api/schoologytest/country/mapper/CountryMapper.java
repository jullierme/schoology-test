package com.jullierme.api.schoologytest.country.mapper;

import com.jullierme.api.schoologytest.country.response.CountryResponse;
import com.jullierme.api.schoologytest.domain.Country;

public interface CountryMapper {
    CountryResponse toResponse(Country country);
}
