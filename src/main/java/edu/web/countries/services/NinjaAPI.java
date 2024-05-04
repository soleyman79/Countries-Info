package edu.web.countries.services;

import edu.web.countries.exceptions.CountryNotFound;
import edu.web.countries.models.ninja.Country;
import edu.web.countries.models.ninja.CountryDTO;
import edu.web.countries.models.ninja.Weather;
import edu.web.countries.models.ninja.WeatherDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NinjaAPI {
    private final RestTemplate restTemplate;
    private final HttpHeaders headers;

    public NinjaAPI(RestTemplate restTemplate, HttpHeaders headers) {
        this.restTemplate = restTemplate;
        this.headers = headers;
    }

    public CountryDTO getCountryByName(String name) {
        Country[] countries = this.getCountryResponseByName(name).getBody();
        if (countries.length == 0) {
            throw new CountryNotFound();
        }
        return new CountryDTO(countries[0]);
    }

    public WeatherDTO getWeatherByCountry(String name) {
        CountryDTO countryDTO = this.getCountryByName(name);
        Weather weather = this.getWeatherResponseByCity(countryDTO.getCapital()).getBody();
        return new WeatherDTO(countryDTO.getName(), countryDTO.getCapital(), weather);
    }

    private ResponseEntity<Country[]> getCountryResponseByName(String name) {
        HttpEntity<String> request = new HttpEntity<>(this.headers);
        return restTemplate.exchange(String.format("https://api.api-ninjas.com/v1/country?name=%s", name), HttpMethod.GET, request, Country[].class);
    }

    private ResponseEntity<Weather> getWeatherResponseByCity(String city) {
        HttpEntity<String> request = new HttpEntity<>(this.headers);
        return restTemplate.exchange(String.format("https://api.api-ninjas.com/v1/weather?city=%s", city), HttpMethod.GET, request, Weather.class);
    }
}
