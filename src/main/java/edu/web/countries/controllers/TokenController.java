package edu.web.countries.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/user/api-tokens")
@PreAuthorize("hasRole('USER')")
public class TokenController {
    @PostMapping("")
    public Map<String, Object> createToken() {
        return null;
    }

    @GetMapping("")
    public Map<String, Object> getTokens() {
        return null;

    }

    @DeleteMapping("")
    public Map<String, Object> deleteToken() {
        return null;

    }
}
