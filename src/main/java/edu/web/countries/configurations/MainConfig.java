package edu.web.countries.configurations;

import edu.web.countries.controllers.CountryController;
import edu.web.countries.services.NinjaAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MainConfig {
    @Bean
    public RestTemplate restTemplate(MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter) {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(mappingJackson2HttpMessageConverter);
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
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
