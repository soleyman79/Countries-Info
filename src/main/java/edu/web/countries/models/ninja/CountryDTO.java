package edu.web.countries.models.ninja;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@AllArgsConstructor
@ToString
public class CountryDTO {
    private String name;
    private String capital;
    private String iso2;
    private String population;
    private String popGrowth;
    private Currency currency;

    public CountryDTO(Country country) {
        this.name = country.getName();
        this.capital = country.getCapital();
        this.iso2 = country.getIso2();
        this.population = country.getPopulation();
        this.popGrowth = country.getPop_growth();
        this.currency = country.getCurrency();
    }

    public Map<String, Object> getHashMap() {
        return Map.of(
                "name", this.name,
                "capital", this.capital,
                "iso2", this.iso2,
                "population", this.population,
                "pop_growth", this.popGrowth,
                "currency", this.currency
        );
    }
}
