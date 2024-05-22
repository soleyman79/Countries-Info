package edu.web.countries.controllers;

import edu.web.countries.models.EndUser.EndUser;
import edu.web.countries.repositories.EndUserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final EndUserRepository endUserRepository;

    public AdminController(EndUserRepository endUserRepository) {
        this.endUserRepository = endUserRepository;
    }

    @PutMapping("/users")
    public String activate(@RequestParam("username") String username, @RequestParam("active") boolean active) {
        Optional<EndUser> endUser = endUserRepository.findEndUserByUsername(username);
        if (endUser.isEmpty())
            throw new UsernameNotFoundException("Username not Found");

//        endUser.get().
//        this.endUserRepository.(endUser.get());
        return String.format("User: %s\nActive: %s", username, active);
    }

    @GetMapping("/users")
    public Map<String, Object> getAllUsers() {
        return Map.of("Users", this.endUserRepository.findAll());
    }
}
