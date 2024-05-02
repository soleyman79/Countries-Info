package edu.web.countries.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Country {
    private String iso2;
    private String iso3;
    private String country;
    private String[] cities;
}
