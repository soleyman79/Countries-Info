package edu.web.countries.controllers;

import edu.web.countries.models.Jwt.RegisterAndLoginRequest;
import edu.web.countries.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private AuthenticationService authenticationService;

    public UserController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody RegisterAndLoginRequest request) {
        authenticationService.register(request);
        return Map.of("message", "User Created");
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody RegisterAndLoginRequest request) {
        return Map.of("token", this.authenticationService.login(request).getToken());
    }
}
