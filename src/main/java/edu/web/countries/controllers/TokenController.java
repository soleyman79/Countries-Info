package edu.web.countries.controllers;

import edu.web.countries.models.EndUser.EndUser;
import edu.web.countries.models.Jwt.Token;
import edu.web.countries.repositories.TokenRepo;
import edu.web.countries.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/user/api-tokens")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
public class TokenController {
    private final JwtService jwtService;
    private final TokenRepo tokenRepo;

    @PostMapping("")
    public Map<String, Object> createToken(@RequestBody Token token, @AuthenticationPrincipal EndUser user) {
        token.setUsername(user.getUsername());
        String jwtToken = this.jwtService.generateToken(user, token.getExpireDuration());
        token.setToken(jwtToken);
        this.tokenRepo.save(token);
        return Map.of(
                "name", token.getName(),
                "expire_date", token.getExpireDate(),
                "token", jwtToken
        );
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
