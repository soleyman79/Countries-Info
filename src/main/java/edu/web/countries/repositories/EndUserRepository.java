package edu.web.countries.repositories;

import edu.web.countries.models.EndUser.EndUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EndUserRepository extends CrudRepository<EndUser, Integer> {
    Optional<EndUser> findEndUserByUsername(String username);
}
