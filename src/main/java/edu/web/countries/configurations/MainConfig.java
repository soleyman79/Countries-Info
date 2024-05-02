package edu.web.countries.configurations;

import edu.web.countries.controllers.CountryController;
import edu.web.countries.services.NinjaAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MainConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean
    public NinjaAPI externalAPI(RestTemplate restTemplate) {
        return new NinjaAPI(restTemplate);
    }

    @Bean
    public CountryController countryController(NinjaAPI externalAPI) {
        return new CountryController(externalAPI);
    }
}
