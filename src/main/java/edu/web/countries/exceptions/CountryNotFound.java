package edu.web.countries.exceptions;

public class CountryNotFound extends RuntimeException {
    public CountryNotFound() {
        super("Country Not Found");
    }
}
