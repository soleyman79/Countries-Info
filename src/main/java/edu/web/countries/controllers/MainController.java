package edu.web.countries.controllers;

import edu.web.countries.services.CountriesNowAPI;
import edu.web.countries.services.NinjaAPI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String getAllCountries() {
        return this.countriesNowAPI.getAllCountries().getBody();
    }

    @GetMapping("/{name}")
    public String getCountryByName(@PathVariable String name) {
        return this.ninjaAPI.getCountryByName(name).getBody().toString();
    }
}
