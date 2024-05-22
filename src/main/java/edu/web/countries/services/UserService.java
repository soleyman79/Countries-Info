package edu.web.countries.services;

import edu.web.countries.exceptions.ConflictException;
import edu.web.countries.exceptions.NotFoundException;
import edu.web.countries.models.EndUser.EndUser;
import edu.web.countries.repositories.EndUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final EndUserRepository endUserRepository;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DisabledException {
                Optional<EndUser> endUser = endUserRepository.findEndUserByUsername(username);
                if (endUser.isEmpty())
                    throw new NotFoundException("Username not Found");
                if (!endUser.get().isEnabled())
                    throw new ConflictException("User is Disabled");
                return endUser.get();
            }
        };
    }

    public EndUser save(EndUser newUser) {
        return endUserRepository.save(newUser);
    }
}
