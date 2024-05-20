package edu.web.countries.controllers;

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
}
