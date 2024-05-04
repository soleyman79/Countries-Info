package edu.web.countries.controllers;

import edu.web.countries.models.countriesNow.CountryDTO;
import edu.web.countries.services.CountriesNowAPI;
import edu.web.countries.services.NinjaAPI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/countries")
public class MainController {
    private final NinjaAPI ninjaAPI;
    private final CountriesNowAPI countriesNowAPI;

    public MainController(NinjaAPI externalAPI, CountriesNowAPI countriesNowAPI) {
        this.ninjaAPI = externalAPI;
        this.countriesNowAPI = countriesNowAPI;
    }

    @GetMapping("")
    public Map<String, Object> getAllCountries() {
        Map<String, Object> map = new HashMap<>();
        List<CountryDTO> countries = this.countriesNowAPI.getAllCountries();
        map.put("countries", countries);
        map.put("count", String.valueOf(countries.size()));
        return map;
    }

    @GetMapping("/{name}")
    public String getCountryByName(@PathVariable String name) {
        return this.ninjaAPI.getCountryByName(name).getBody().toString();
    }
}
