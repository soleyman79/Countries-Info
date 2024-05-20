package edu.web.countries.services;

import edu.web.countries.models.EndUser.EndUser;
import edu.web.countries.repositories.EndUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final EndUserRepository endUserRepository;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return endUserRepository.findEndUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not Found"));
            }
        };
    }

    public EndUser save(EndUser newUser) {
        return endUserRepository.save(newUser);
    }
}
