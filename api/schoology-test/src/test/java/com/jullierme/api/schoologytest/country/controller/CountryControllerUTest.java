package com.jullierme.api.schoologytest.country.controller;

import com.jullierme.api.schoologytest.country.CountryFindService;
import com.jullierme.api.schoologytest.country.response.CountryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@DisplayName("CountryController class unit test suite")
class CountryControllerUTest {
    @MockBean
    private CountryApiService countryApiService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should get a list of countries when find")
    void givenAPartOfTheName_whenFind_thenShouldReturnAListOfCountries() throws Exception {
        //given
        String partOfTheName = "braz";
        String name = "Brazil";
        String abbreviation = "BR";

        CountryResponse brazil = dummyCountryResponse(name, abbreviation);

        doReturn(Collections.singletonList(brazil))
                .when(countryApiService)
                .findByName(any(), any());

        //when
        mockMvc
                .perform(get("/country/find-by-name/{name}", partOfTheName)
                        .accept(APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(name)))
                .andExpect(jsonPath("$[0].abbreviation", is(abbreviation)));
    }

    @Test
    @DisplayName("Should get a list of 3 items when paginate")
    void givenAPartOfTheName_whenFind_thenShouldReturn3elements() throws Exception {
        //given
        String partOfTheName = "braz";
        String name = "Brazil";
        String abbreviation = "BR";

        CountryResponse brazil = dummyCountryResponse(name, abbreviation);

        doReturn(Arrays.asList(brazil, brazil, brazil))
                .when(countryApiService)
                .findByName(any(), any());

        //when
        mockMvc
                .perform(get("/country/find-by-name/{name}/?p=1&l=3", partOfTheName)
                        .accept(APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @ParameterizedTest
    @MethodSource("invalidParameters")
    @DisplayName("Should not found any country with invalid parameters")
    void givenInvalidParameters_whenFind_thenShouldReturnAListOfCountries(String invalidName) throws Exception {
        //given parameters
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND))
                .when(countryApiService)
                .findByName(any(), any());

        //when
        mockMvc
                .perform(get("/country/find-by-name/{name}", invalidName)
                        .accept(APPLICATION_JSON))
                //then
                .andExpect(status().isNotFound());

    }

    private CountryResponse dummyCountryResponse(String name, String abbreviation) {
        return CountryResponse.builder()
                .name(name)
                .abbreviation(abbreviation)
                .build();
    }

    private static Stream<String> invalidParameters() {
        return Stream.of("", "zzzz", "aaaa", null);
    }
}
