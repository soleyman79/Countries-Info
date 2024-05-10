package edu.web.countries.exceptions;

public class UserNotFound extends RuntimeException {
    public UserNotFound() {
        super("User Not Found");
    }
}
