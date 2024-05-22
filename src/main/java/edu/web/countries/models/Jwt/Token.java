package edu.web.countries.models.Jwt;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String token;
    @JsonProperty("name")
    private String name;
    @JsonProperty("expire_date")
    private String expireDate;
    private String username;

    public Long getExpireDuration() {
        return Instant.parse(this.expireDate).toEpochMilli() - Instant.now().toEpochMilli();
    }
}
