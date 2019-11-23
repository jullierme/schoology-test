package com.jullierme.api.schoologytest.country.controller;

import com.jullierme.api.schoologytest.country.CountryFindService;
import com.jullierme.api.schoologytest.country.mapper.CountryMapper;
import com.jullierme.api.schoologytest.country.response.CountryResponse;
import com.jullierme.api.schoologytest.domain.Country;
import com.jullierme.api.schoologytest.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("CountryApiService class unit test suite")
class CountryApiServiceUTest {
    @Mock
    private CountryMapper countryMapper;

    @Mock
    private CountryFindService countryFindService;

    private CountryApiService countryApiService;

    @BeforeEach
    void setUp() {
        countryApiService = new CountryApiServiceImpl(countryFindService, countryMapper);
    }

    @Test
    @DisplayName("Should convert a List of Country in a List of CountryResponse")
    void givenName_whenFindByName_thenShouldConvertTheResponse() {
        //given
        String name = "Brazil";
        String abbreviation = "BR";
        mockCountryAndResponse(name, abbreviation, 1);

        //when
        List<CountryResponse> list = countryApiService.findByName(name);

        //then
        assertNotNull(list);
        assertEquals(name, list.get(0).getName());
        assertEquals(abbreviation, list.get(0).getAbbreviation());

        verify(countryMapper).toResponse(any());
        verify(countryFindService).findByName(any(), any());
    }

    @Test
    @DisplayName("Should get a list of 3 items when paginate")
    void givenAPartOfTheName_whenFind_thenShouldReturn3elements() throws Exception {
        //given
        String partOfTheName = "braz";
        String name = "Brazil";
        String abbreviation = "BR";

        mockCountryAndResponse(name, abbreviation, 3);

        //when
        List<CountryResponse> list = countryApiService.findByName(name);

        //then
        assertNotNull(list);
        assertEquals(list.size(), 3);
    }


    @ParameterizedTest
    @MethodSource("invalidParameters")
    @DisplayName("Should not found any country with invalid parameters")
    void givenAPartOfTheName_whenFind_thenShouldReturnAListOfCountries(String invalidName) throws Exception {
        //given
        doReturn(Optional.empty())
                .when(countryFindService).findByName(any(), any());

        //when
        Executable executable = () -> countryApiService.findByName(invalidName);

        //then
        assertThrows(NotFoundException.class, executable);
    }

    private static Stream<String> invalidParameters() {
        return Stream.of("", "zzzz", "aaaa", null);
    }

    private void mockCountryAndResponse(String name, String abbreviation, int elements) {
        CountryResponse response = CountryResponse.builder()
                .name(name)
                .abbreviation(abbreviation)
                .build();

        Country country = Country.builder()
                .name(name)
                .abbreviation(abbreviation)
                .build();

        doReturn(response)
                .when(countryMapper).toResponse(country);

        List<Country> list = new ArrayList<>();

        for (int x = 0; x < elements; x++) {
            list.add(country);
        }

        doReturn(Optional.of(list))
                .when(countryFindService).findByName(anyString(), any());
    }
}
