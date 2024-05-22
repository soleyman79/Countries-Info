package edu.web.countries.controllers;

import edu.web.countries.exceptions.ServerException;
import edu.web.countries.models.EndUser.EndUser;
import edu.web.countries.models.Jwt.Token;
import edu.web.countries.repositories.TokenRepo;
import edu.web.countries.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


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
    public Map<String, Object> getTokens(@AuthenticationPrincipal EndUser user) {
        Optional<List<Token>> optionalTokens = this.tokenRepo.findAllByUsername(user.getUsername());
        List<Token> tokens = optionalTokens.orElseGet(ArrayList::new);
        return Map.of(
                "tokens", tokens.stream()
                        .filter(Token::isValid)
                        .map(Token::getDto)
                        .collect(Collectors.toList()),
                "count", tokens.stream()
                        .filter(Token::isValid)
                        .toList()
                        .size()
        );
    }

    @DeleteMapping("")
    public Map<String, Object> deleteToken(@RequestHeader("Authorization") String header) {
        Optional<Token> token = this.tokenRepo.findByToken(header.substring(7));
        if (token.isEmpty())
            throw new ServerException("Token is Permanent");
        token.get().setValid(false);
        this.tokenRepo.save(token.get());
        return Map.of(
                "deleted", "true"
        );
    }
}
