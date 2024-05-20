package edu.web.countries.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user/api-tokens")
public class TokenController {
    @PostMapping("")
    public String createToken() {
        return "a";
    }

    @GetMapping("")
    public String getTokens() {
        return "a";
    }

    @DeleteMapping("")
    public String deleteToken() {
        return "a";
    }


    @GetMapping("/anon")
    public String anonEndPoint() {
        return "everyone can see this";
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('USER')")
    public String usersEndPoint() {
        return "ONLY users can see this";
    }

    @GetMapping("/admins")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminsEndPoint() {
        return "ONLY admins can see this";
    }
}
