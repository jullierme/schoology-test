package com.jullierme.api.schoologytest.country.mapper;

import com.jullierme.api.schoologytest.country.response.CountryResponse;
import com.jullierme.api.schoologytest.domain.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("CountryMapper class unit test suite")
class CountryMapperTest {
    CountryMapper countryMapper;

    @BeforeEach
    void setUp() {
        countryMapper = new CountryMapperImpl();
    }

    @Test
    @DisplayName("should convert a Country to CountryResponse")
    void givenObjectCountry_whenToResponse_thenShouldConvertToResponse() {
        //given
        String name = "Brazil";
        String abbreviation = "BR";
        Country country = Country.builder()
                .name(name)
                .abbreviation(abbreviation)
                .build();

        //when
        CountryResponse response = countryMapper.toResponse(country);

        //then
        assertNotNull(response);
        assertEquals(name, response.getName());
        assertEquals(abbreviation, response.getAbbreviation());
    }
}
