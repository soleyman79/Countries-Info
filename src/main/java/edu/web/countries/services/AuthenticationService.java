package edu.web.countries.services;

import edu.web.countries.models.EndUser.EndUser;
import edu.web.countries.models.EndUser.Role;
import edu.web.countries.models.Jwt.JwtAuthenticationResponse;
import edu.web.countries.models.Jwt.RegisterAndLoginRequest;
import edu.web.countries.repositories.EndUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final EndUserRepository endUserRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse register(RegisterAndLoginRequest request) {
        var user = EndUser
                .builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        user = userService.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    public JwtAuthenticationResponse login(RegisterAndLoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        EndUser user = endUserRepository.findEndUserByUsername(request.getUsername()).orElseThrow(() -> new IllegalArgumentException("Invalid Username or Password"));
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}