package edu.web.countries.configurations;

import edu.web.countries.controllers.AdminController;
import edu.web.countries.controllers.MainController;
import edu.web.countries.controllers.UserController;
import edu.web.countries.repositories.EndUserRepo;
import edu.web.countries.services.AuthenticationService;
import edu.web.countries.services.CountriesNowAPI;
import edu.web.countries.services.NinjaAPI;
import edu.web.countries.services.messageBroker.Producer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MainConfig {
    @Bean
    public MainController mainController(NinjaAPI externalAPI, CountriesNowAPI countriesNowAPI, Producer producer) {
        return new MainController(externalAPI, countriesNowAPI, producer);
    }

    @Bean
    public AdminController adminController(EndUserRepo endUserRepository) {
        return new AdminController(endUserRepository);
    }

    @Bean
    public UserController userController(AuthenticationService authenticationService) {
        return new UserController(authenticationService);
    }
}
