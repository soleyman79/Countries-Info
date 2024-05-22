package edu.web.countries.services;

import edu.web.countries.exceptions.ConflictException;
import edu.web.countries.models.EndUser.EndUser;
import edu.web.countries.models.EndUser.Role;
import edu.web.countries.models.Jwt.JwtAuthenticationResponse;
import edu.web.countries.models.Jwt.RegisterAndLoginRequest;
import edu.web.countries.models.Jwt.Token;
import edu.web.countries.repositories.EndUserRepo;
import edu.web.countries.repositories.TokenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final EndUserRepo endUserRepo;
    private final TokenRepo tokenRepo;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse register(RegisterAndLoginRequest request) {
        EndUser user = EndUser
                .builder()
                .username(request.getUsername())
                .password(this.passwordEncoder.encode(request.getPassword()))
                .active(true)
                .role(Role.ROLE_USER)
                .build();
        if (this.endUserRepo.findEndUserByUsername(request.getUsername()).isPresent())
            throw new ConflictException("Username already Exists");

        user = this.userService.save(user);
        String jwt = this.jwtService.generateToken(user, 0L);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    public JwtAuthenticationResponse login(RegisterAndLoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        EndUser user = this.endUserRepo.findEndUserByUsername(request.getUsername()).orElseThrow(() -> new IllegalArgumentException("Invalid Username or Password"));
        String jwt = this.jwtService.generateToken(user, 3600000L);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}