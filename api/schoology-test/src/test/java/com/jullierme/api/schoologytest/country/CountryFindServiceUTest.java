package com.jullierme.api.schoologytest.country;

import com.jullierme.api.schoologytest.domain.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("CountryFindService class unit test suite")
class CountryFindServiceUTest {

    @Mock
    private CountryFindRepository countryRepository;

    private CountryFindService countryService;

    @BeforeEach
    void setUp() {
        countryService = new CountryFindServiceImpl(countryRepository);
    }

    @Test
    void givenName_whenFindByName_thenShouldFindARecord() {
        //given
        String partOfTheName = "braz";
        String name = "Brazil";
        String abbreviation = "BR";
        Integer expectedListSize = 1;

        Country brazil = dummyCountry(name, abbreviation);

        doReturn(Optional.of(Collections.singletonList(brazil)))
                .when(countryRepository)
                .findByName(any(), any());

        //when
        List<Country> countries = countryService.findByName(partOfTheName)
                .orElse(null);

        //then
        assertNotNull(countries);
        assertEquals(expectedListSize, countries.size());
        assertEquals(name, countries.get(0).getName());
        assertEquals(abbreviation, countries.get(0).getAbbreviation());

        verify(countryRepository).findByName(anyString(), any());
    }

    private Country dummyCountry(String name, String abbreviation) {
        return Country.builder()
                .name(name)
                .abbreviation(abbreviation)
                .build();
    }
}
