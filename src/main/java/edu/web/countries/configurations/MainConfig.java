package edu.web.countries.configurations;

import edu.web.countries.controllers.MainController;
import edu.web.countries.services.CountriesNowAPI;
import edu.web.countries.services.NinjaAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig {

    @Bean
    public MainController mainController(NinjaAPI externalAPI, CountriesNowAPI countriesNowAPI) {
        return new MainController(externalAPI, countriesNowAPI);
    }
}
