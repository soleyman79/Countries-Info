package edu.web.countries.models.EndUser;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class EndUser {
    @Id
    int id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    private boolean active;
    private Rule rule;
}
