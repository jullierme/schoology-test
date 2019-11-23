package com.jullierme.api.schoologytest.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Country class suite tests")
class CountryUTest {

    @Test
    @DisplayName("Should creat an")
    void givenNameAndAbbreviation_whenNew_thenShouldCreatAnInstance() {
        //given
        String name = "Brazil";
        String abbreviation = "BR";

        //when
        Country country = dummyCountry(name, abbreviation);

        //then
        assertNotNull(country);
        assertEquals(name, country.getName());
        assertEquals(abbreviation, country.getAbbreviation());
    }

    private Country dummyCountry(String name, String abbreviation) {
        return Country.builder()
                .name(name)
                .abbreviation(abbreviation)
                .build();
    }
}
