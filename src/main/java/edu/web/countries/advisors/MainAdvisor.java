package edu.web.countries.advisors;

import edu.web.countries.exceptions.CountryNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(name = "MainController")
public class MainAdvisor {
    @ExceptionHandler(CountryNotFound.class)
    public ResponseEntity<String> handleException(CountryNotFound countryNotFound) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(countryNotFound.getMessage());
    }
}
