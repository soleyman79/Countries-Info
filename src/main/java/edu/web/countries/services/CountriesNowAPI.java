package edu.web.countries.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CountriesNowAPI {
    @Autowired
    @Qualifier("countriesNowTemplate")
    private RestTemplate restTemplate;

    public ResponseEntity<String> getAllCountries() {
        return restTemplate.getForEntity("https://countriesnow.space/api/v0.1/countries", String.class);
    }
}
