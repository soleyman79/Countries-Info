package edu.web.countries.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalAPI {
    private final RestTemplate restTemplate;

    public ExternalAPI(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public ResponseEntity<String> getAllCountries() {
        return restTemplate.getForEntity("https://countriesnow.space/api/v0.1/countries", String.class);
    }
}
