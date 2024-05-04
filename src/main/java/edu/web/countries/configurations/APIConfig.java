package edu.web.countries.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.web.countries.services.CountriesNowAPI;
import edu.web.countries.services.NinjaAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class APIConfig {
    @Bean
    public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        return converter;
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, mappingJacksonHttpMessageConverter());
        return restTemplate;
    }

    @Bean
    public NinjaAPI ninjaAPI(RestTemplate restTemplate) {
        return new NinjaAPI(restTemplate);
    }

    @Bean
    public CountriesNowAPI countriesNowAPI(RestTemplate restTemplate) {
        return new CountriesNowAPI(restTemplate);
    }
}
