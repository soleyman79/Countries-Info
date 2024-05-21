package edu.web.countries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CountriesApplication {
    public static void main(String[] args) {
        SpringApplication.run(CountriesApplication.class, args);
    }
}
