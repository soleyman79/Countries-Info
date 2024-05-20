package edu.web.countries.controllers;

import edu.web.countries.exceptions.UserNotFound;
import edu.web.countries.models.EndUser.EndUser;
import edu.web.countries.repositories.EndUserRepository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/admin")
public class AdminController {
    private final EndUserRepository endUserRepository;

    public AdminController(EndUserRepository endUserRepository) {
        this.endUserRepository = endUserRepository;
    }

    @PutMapping("/users")
    public String activate(@RequestParam("username") String username, @RequestParam("active") boolean active) {
        Optional<EndUser> endUser = endUserRepository.findEndUserByUsername(username);
        if (endUser.isEmpty())
            throw new UserNotFound();

        endUser.get().setActive(active);
        this.endUserRepository.save(endUser.get());
        return String.format("User: %s\nActive: %s", username, active);
    }
}
