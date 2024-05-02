package edu.web.countries.services;

import edu.web.countries.models.Country;
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


    public ResponseEntity<String> getAllCountries() {
        return restTemplate.getForEntity("https://countriesnow.space/api/v0.1/countries", String.class);
    }

    public ResponseEntity<String> getCountryByName(String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", this.token);
        HttpEntity<String> request = new HttpEntity<>(headers);
        return restTemplate.exchange(
                String.format("https://api.api-ninjas.com/v1/country?name=%s", name),
                HttpMethod.GET,
                request,
                String.class // TODO change to Country.class
        );
    }
}
