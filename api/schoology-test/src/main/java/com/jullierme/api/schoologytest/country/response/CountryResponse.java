package com.jullierme.api.schoologytest.country.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class CountryResponse {
    private String name;
    private String abbreviation;
}
