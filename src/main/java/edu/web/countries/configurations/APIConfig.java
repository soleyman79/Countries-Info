package edu.web.countries.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.web.countries.services.CountriesNowAPI;
import edu.web.countries.services.NinjaAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableCaching
public class APIConfig {
    @Value("${token.ninja}")
    private String token;

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
    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", this.token);
        headers.set("Content-Type", "application/json");
        return headers;
    }

    @Bean
    public NinjaAPI ninjaAPI(RestTemplate restTemplate, HttpHeaders httpHeaders) {
        return new NinjaAPI(restTemplate, httpHeaders);
    }

    @Bean
    public CountriesNowAPI countriesNowAPI(RestTemplate restTemplate) {
        return new CountriesNowAPI(restTemplate);
    }
}
