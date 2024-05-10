package edu.web.countries.exceptions;

public class UserAlreadyExits extends RuntimeException {
    public UserAlreadyExits() {
        super("User Already Exists");
    }
}

