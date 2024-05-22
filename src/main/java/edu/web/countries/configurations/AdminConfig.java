package edu.web.countries.configurations;

import edu.web.countries.models.EndUser.EndUser;
import edu.web.countries.models.EndUser.Role;
import edu.web.countries.repositories.EndUserRepo;
import edu.web.countries.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class AdminConfig implements CommandLineRunner {
    private final EndUserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    @Value("${admin.username}")
    private String adminUsername;
    @Value("${admin.password}")
    private String adminPassword;


    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            EndUser admin = EndUser
                    .builder()
                    .username(this.adminUsername)
                    .password(passwordEncoder.encode(this.adminPassword))
                    .role(Role.ROLE_ADMIN)
                    .active(true)
                    .build();

            userService.save(admin);
            log.debug("ADMIN Created - {}", admin);
        }
    }
}