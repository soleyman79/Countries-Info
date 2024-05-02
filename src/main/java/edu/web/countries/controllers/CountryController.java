package edu.web.countries.controllers;

import edu.web.countries.services.NinjaAPI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/countries")
public class CountryController {
    private final NinjaAPI ninjaAPI;

    public CountryController(NinjaAPI externalAPI) {
        this.ninjaAPI = externalAPI;
    }

    @GetMapping("")
    public String getAllCountries() {
        return this.ninjaAPI.getAllCountries().getBody();
    }

    @GetMapping("/{name}")
    public String getCountryByName(@PathVariable String name) {
        return this.ninjaAPI.getCountryByName(name).getBody();
    }
}
