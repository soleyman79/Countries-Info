package edu.web.countries.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/countries")
    public String getAllCountries() {
        return null;
    }
}
