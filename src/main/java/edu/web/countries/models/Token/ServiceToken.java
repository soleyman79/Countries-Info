package edu.web.countries.models.Token;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
public class ServiceToken {
    @Id
    int id;
    private String name;
    private UUID uuid;
    private String username;
    private Date expireDate;
}
