package edu.web.countries.services;

import edu.web.countries.models.EndUser;
import edu.web.countries.repositories.EndUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EndUserService {
    private final EndUserRepository endUserRepository;

    public EndUserService(EndUserRepository endUserRepository) {
        this.endUserRepository = endUserRepository;
    }

    public boolean isUserExist(String username) {
        Optional<EndUser> endUser = this.endUserRepository.findEndUserByUsername(username);
        return endUser.isPresent();
    }

    public void saveUser(EndUser endUser) {
        this.endUserRepository.save(endUser);
    }
}
