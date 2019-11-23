package com.jullierme.api.schoologytest.country;

import com.jullierme.api.schoologytest.domain.Country;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("CountryFindRepository class integration test suite")
class CountryFindRepositoryITest {
    @Autowired
    private CountryFindRepository repository;

    /*@Test
    @DisplayName("Should create a new record")
    void givenParameters_whenSave_thenShouldCreateNewRecord() {
        //given
        String name = "Brazil";
        String abbreviation = "BR";
        Country country = Country.builder()
                .name(name)
                .abbreviation(abbreviation)
                .build();
        //when
        country = createRepository.save(country);

        //then
        assertNotNull(country);
        assertNotNull(country.getId());
        assertEquals(name, country.getName());
        assertEquals(abbreviation, country.getAbbreviation());
    }*/


    @ParameterizedTest
    @ValueSource(strings = {"raZil", "razil", "RAZIL", "BRAZIL", "brazil"})
    @DisplayName("Should find the records with part of the name")
    void givenPartOfTheNameBrazil_whenFindByName_thenShouldFindARecord(String name) {
        //given
        String expectedCountry = "Brazil";
        String expectedAbbreviation = "BR";

        //when
        List<Country> countries = repository.findByName(name)
                .orElse(null);

        //then
        assertNotNull(countries);
        assertNotNull(countries.get(0).getId());
        assertEquals(expectedCountry, countries.get(0).getName());
        assertEquals(expectedAbbreviation, countries.get(0).getAbbreviation());
    }

    @Test
    @DisplayName("Should find the records that contains the letter 'a' limited to 7 first records")
    void givenPartOfTheName_whenFindByName_thenShouldFindARecordWithLimit() {
        //given
        String partOfTheName = "a";
        int limit = 7;

        //when
        List<Country> countries = repository.findByName(partOfTheName, PageRequest.of(0, limit))
                .orElse(null);

        //then
        assertNotNull(countries);
        assertEquals(limit, countries.size());
    }

    @ParameterizedTest
    @MethodSource("invalidParameters")
    @DisplayName("Should not found any country with invalid parameters")
    void givenPartOfTheName_whenFindByName_thenShouldFindARecordWithLimit(String inv) {
        //given parameters
        //when
        List<Country> countries = repository.findByName(inv)
                .orElse(null);

        //then
        assertNull(countries);
    }

    private static Stream<String> invalidParameters() {
        return Stream.of("", "zzzz", "aaaa", null);
    }
}
