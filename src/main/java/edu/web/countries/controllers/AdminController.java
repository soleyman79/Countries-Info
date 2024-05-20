package edu.web.countries.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin")
public class AdminController {
    @PutMapping("/users")
    public String activate(@PathVariable("username") String username, @PathVariable("active") String active) {
        return username + active;
    }
}
