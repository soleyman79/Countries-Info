package edu.web.countries.services;

import edu.web.countries.exceptions.UnauthorizedException;
import edu.web.countries.models.Jwt.Token;
import edu.web.countries.repositories.TokenRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;


@RequiredArgsConstructor
public class MainInterceptor implements HandlerInterceptor {
    private final TokenRepo tokenRepo;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Optional<Token> token = this.tokenRepo.findByToken(request.getHeader("Authorization").substring(7));
        if (!this.validateToken(token))
            throw new UnauthorizedException("Token has been Deleted");
        return true;
    }

    public boolean validateToken(Optional<Token> token) {
        return token.isEmpty() || token.get().isValid();
    }
}