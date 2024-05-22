package edu.web.countries.repositories;

import edu.web.countries.models.Jwt.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TokenRepo extends JpaRepository<Token, Long> {
    Optional<List<Token>> findAllByUsername(String username);
}
