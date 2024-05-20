package edu.web.countries.services;

import edu.web.countries.models.EndUser.EndUser;
import edu.web.countries.repositories.EndUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;


public class EndUserDetailsService implements UserDetailsService {
    private final EndUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.boot.admin.client.username}")
    private String adminUsername;

    @Value("${spring.boot.admin.client.password}")
    private String adminPassword;

    public EndUserDetailsService(EndUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals(this.adminUsername)) {
            return User
                    .withUsername(this.adminUsername)
                    .password(this.passwordEncoder.encode(this.adminPassword))
                    .roles("admin")
                    .build();
        }
        Optional<EndUser> endUser = this.userRepository.findEndUserByUsername(username);
        if (endUser.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Username Not Found: %s", username));
        }
        return User.withUsername(username).password(endUser.get().getPassword()).build();
    }
}
