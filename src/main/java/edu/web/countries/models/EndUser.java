package edu.web.countries.models;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
}
