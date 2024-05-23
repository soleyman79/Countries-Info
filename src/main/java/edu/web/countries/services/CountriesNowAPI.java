package edu.web.countries.services;

import edu.web.countries.models.countriesNow.Country;
import edu.web.countries.models.countriesNow.CountryDTO;
import edu.web.countries.models.countriesNow.Response;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable("countries")
    public List<CountryDTO> getAllCountries() {
        Response response = this.getAllCountriesResponse();
        Country[] countries = response.getData();
        List<CountryDTO> countryDTOs = new ArrayList<>();
        for (Country country : countries) {
            countryDTOs.add(new CountryDTO(country));
        }
        return countryDTOs;
    }


    public Response getAllCountriesResponse() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<Response> response = restTemplate.exchange(
                "https://countriesnow.space/api/v0.1/countries",
                HttpMethod.GET,
                request,
                Response.class
        );
        return response.getBody();
    }
}
