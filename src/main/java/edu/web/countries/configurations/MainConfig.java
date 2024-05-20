package edu.web.countries.configurations;

import edu.web.countries.controllers.AdminController;
import edu.web.countries.controllers.MainController;
import edu.web.countries.controllers.UserController;
import edu.web.countries.repositories.EndUserRepository;
import edu.web.countries.services.CountriesNowAPI;
import edu.web.countries.services.EndUserService;
import edu.web.countries.services.NinjaAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class MainConfig {
    @Bean
    public MainController mainController(NinjaAPI externalAPI, CountriesNowAPI countriesNowAPI) {
        return new MainController(externalAPI, countriesNowAPI);
    }

    @Bean
    public EndUserService endUserService(EndUserRepository endUserRepository) {
        return new EndUserService(endUserRepository);
    }

    @Bean
    UserController userController(EndUserService endUserService, PasswordEncoder passwordEncoder) {
        return new UserController(endUserService, passwordEncoder);
    }

    @Bean
    AdminController adminController(EndUserRepository endUserRepository) {
        return new AdminController(endUserRepository);
    }
}
