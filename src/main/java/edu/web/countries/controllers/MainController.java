package edu.web.countries.controllers;

import edu.web.countries.services.ExternalAPI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    private final ExternalAPI externalAPI;

    public MainController(ExternalAPI externalAPI) {
        this.externalAPI = externalAPI;
    }

    @GetMapping("/countries")
    public String getAllCountries() {
        return this.externalAPI.getAllCountries().getBody();
    }
}
