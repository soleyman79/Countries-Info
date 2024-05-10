package edu.web.countries.advisors;

import edu.web.countries.exceptions.CountryNotFound;
import edu.web.countries.exceptions.UserAlreadyExits;
import edu.web.countries.exceptions.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;


@RestControllerAdvice
public class MainAdvisor {
    @ExceptionHandler({CountryNotFound.class, UserNotFound.class})
    public ResponseEntity<Map<String, Object>> handleNotFoundExceptions(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", exception.getMessage()));
    }

    @ExceptionHandler(UserAlreadyExits.class)
    public ResponseEntity<Map<String, Object>> handleConflictExceptions(Exception exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", exception.getMessage()));
    }
}
