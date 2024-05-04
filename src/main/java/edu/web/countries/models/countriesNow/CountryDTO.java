package edu.web.countries.models.countriesNow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class CountryDTO {
    private String name;

    public CountryDTO(Country country) {
        this.name = country.getCountry();
    }
}
