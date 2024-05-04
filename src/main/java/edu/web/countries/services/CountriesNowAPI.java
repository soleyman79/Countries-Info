package edu.web.countries.services;

import edu.web.countries.models.countriesNow.Country;
import edu.web.countries.models.countriesNow.CountryDTO;
import edu.web.countries.models.countriesNow.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountriesNowAPI {
    private final RestTemplate restTemplate;

    public CountriesNowAPI(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CountryDTO> getAllCountries() {
        Response response = this.getAllCountriesResponse().getBody();
        Country[] countries = response.getData();
        List<CountryDTO> countryDTOs = new ArrayList<>();
        for (Country country : countries) {
            countryDTOs.add(new CountryDTO(country));
        }
        return countryDTOs;
    }

    private ResponseEntity<Response> getAllCountriesResponse() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<>(headers);
        return restTemplate.exchange(
                "https://countriesnow.space/api/v0.1/countries",
                HttpMethod.GET,
                request,
                Response.class
        );
    }
}
