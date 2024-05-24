package edu.web.countries.controllers;

import edu.web.countries.models.countriesNow.CountryDTO;
import edu.web.countries.services.CountriesNowAPI;
import edu.web.countries.services.NinjaAPI;
import edu.web.countries.services.messageBroker.Producer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/countries")
@PreAuthorize("hasRole('USER')")
public class MainController {
    private final NinjaAPI ninjaAPI;
    private final CountriesNowAPI countriesNowAPI;
    private final Producer producer;

    public MainController(NinjaAPI externalAPI, CountriesNowAPI countriesNowAPI, Producer producer) {
        this.ninjaAPI = externalAPI;
        this.countriesNowAPI = countriesNowAPI;
        this.producer = producer;
    }

    @GetMapping("")
    public Map<String, Object> getAllCountries() {
        this.producer.sendMessage("all countries");
        List<CountryDTO> countries = this.countriesNowAPI.getAllCountries();
        return Map.of(
                "countries", countries,
                "count", String.valueOf(countries.size())
        );
    }

    @GetMapping("/{name}")
    public Map<String, Object> getCountryByName(@PathVariable String name) {
        this.producer.sendMessage(String.format("country %s", name));
        return this.ninjaAPI.getCountryByName(name).getHashMap();
    }

    @GetMapping("/{name}/weather")
    public Map<String, Object> getWeatherByCity(@PathVariable String name) {
        this.producer.sendMessage(String.format("weather %s", name));
        return this.ninjaAPI.getWeatherByCountry(name).getHashMap();
    }
}
