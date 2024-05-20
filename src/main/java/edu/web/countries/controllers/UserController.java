package edu.web.countries.controllers;

import edu.web.countries.exceptions.UserAlreadyExits;
import edu.web.countries.models.EndUser.EndUser;
import edu.web.countries.services.EndUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/users")
public class UserController {
    private final EndUserService endUserService;
    private final PasswordEncoder passwordEncoder;

    public UserController(EndUserService endUserService, PasswordEncoder passwordEncoder) {
        this.endUserService = endUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody EndUser user) {
        if (this.endUserService.isUserExist(user.getUsername()))
            throw new UserAlreadyExits();

        EndUser endUser = new EndUser();
        endUser.setUsername(user.getUsername());
        endUser.setPassword(passwordEncoder.encode(user.getPassword()));
        this.endUserService.saveUser(endUser);
        return Map.of("message", "User Created");
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody EndUser user) {
        if (this.endUserService.isUserExist(user.getUsername()))
            throw new UserAlreadyExits();

        return Map.of("message", "User Created");
    }
}
