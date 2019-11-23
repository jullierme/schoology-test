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
@DisplayName("CountryController class integration test suite")
class CountryControllerITest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Should get a list of countries when find")
    void givenAPartOfTheName_whenFind_thenShouldReturnAListOfCountries() throws Exception {
        //given
        String partOfTheName = "braz";
        String name = "Brazil";
        String abbreviation = "BR";

        //when
        mockMvc
                .perform(get("/country/find-by-name/{name}", partOfTheName)
                        .accept(APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(name)))
                .andExpect(jsonPath("$[0].abbreviation", is(abbreviation)));
    }

    @ParameterizedTest
    @MethodSource("invalidParameters")
    @DisplayName("Should not found any country with invalid parameters")
    void givenInvalidParameters_whenFind_thenShouldReturnAListOfCountries(String invalidName) throws Exception {
        //given parameters

        //when
        mockMvc
                .perform(get("/country/find-by-name/{name}", invalidName)
                        .accept(APPLICATION_JSON))
                //then
                .andExpect(status().isNotFound());

    }

    private static Stream<String> invalidParameters() {
        return Stream.of("", "zzzz", "aaaa", null);
    }
}
