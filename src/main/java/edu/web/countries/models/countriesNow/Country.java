package edu.web.countries.models.countriesNow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
    @JsonProperty("iso2")
    private String iso2;
    @JsonProperty("iso3")
    private String iso3;
    @JsonProperty("country")
    private String country;
    @JsonProperty("cities")
    private String[] cities;
}
