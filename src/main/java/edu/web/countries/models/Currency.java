package edu.web.countries.models;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Currency {
    private String code;
    private String name;
}
