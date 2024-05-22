package edu.web.countries.advisors;

import edu.web.countries.exceptions.NotFoundException;
import edu.web.countries.exceptions.ConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;


@RestControllerAdvice
public class MainAdvisor {
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Map<String, Object>> handleNotFoundExceptions(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", exception.getMessage()));
    }

    @ExceptionHandler({ConflictException.class})
    public ResponseEntity<Map<String, Object>> handleConflictExceptions(Exception exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", exception.getMessage()));
    }
}
