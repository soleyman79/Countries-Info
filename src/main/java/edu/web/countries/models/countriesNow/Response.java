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
public class Response {
    @JsonProperty("error")
    private String error;
    @JsonProperty("msg")
    private String msg;
    @JsonProperty("data")
    private Country[] data;
}
