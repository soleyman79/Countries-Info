package edu.web.countries.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "end_user")
@Data
@NoArgsConstructor
public class EndUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;
    private String username;
    private String password;
}
