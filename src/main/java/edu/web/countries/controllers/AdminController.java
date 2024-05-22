package edu.web.countries.controllers;

import edu.web.countries.exceptions.BadRequestException;
import edu.web.countries.exceptions.NotFoundException;
import edu.web.countries.models.EndUser.EndUser;
import edu.web.countries.repositories.EndUserRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final EndUserRepo endUserRepository;

    public AdminController(EndUserRepo endUserRepository) {
        this.endUserRepository = endUserRepository;
    }

    @PutMapping("/users")
    public Map<String, Object> activate(@RequestParam("username") String username, @RequestParam("active") String active) {
        Optional<EndUser> endUser = endUserRepository.findEndUserByUsername(username);
        if (endUser.isEmpty())
            throw new NotFoundException("Username not Found");
        if (!active.equals("false") && !active.equals("true"))
            throw new BadRequestException("Parameters are Invalid");

        endUser.get().setActive(Boolean.parseBoolean(active));
        this.endUserRepository.save(endUser.get());
        return Map.of("message", String.format("User with username %s has been %s", username, Boolean.parseBoolean(active) ? "Activated" : "Deactivated"));
    }

    @GetMapping("/users")
    public Map<String, Object> getAllUsers() {
        return Map.of("Users", this.endUserRepository.findAll());
    }
}
