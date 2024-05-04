package edu.web.countries.services;

import edu.web.countries.exceptions.CountryNotFound;
import edu.web.countries.models.ninja.Country;
import edu.web.countries.models.ninja.CountryDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NinjaAPI {
    private final RestTemplate restTemplate;
    @Value("${ninja.token}")
    private String token;

    public NinjaAPI(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CountryDTO getCountryByName(String name) {
        Country[] countries = this.getCountryByNameResponse(name).getBody();
        if (countries.length == 0) {
            throw new CountryNotFound();
        }
        return new CountryDTO(countries[0]);
    }

    private ResponseEntity<Country[]> getCountryByNameResponse(String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", this.token);
        headers.set("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<>(headers);
        return restTemplate.exchange(String.format("https://api.api-ninjas.com/v1/country?name=%s", name), HttpMethod.GET, request, Country[].class);
    }
}
