package edu.web.countries.repositories;

import edu.web.countries.models.EndUser.EndUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EndUserRepository extends JpaRepository<EndUser, Integer> {
    Optional<EndUser> findEndUserByUsername(String username);
}
