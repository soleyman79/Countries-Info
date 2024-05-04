package edu.web.countries.models.ninja;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    @JsonProperty("cloud_pct")
    private String cloudPct;
    @JsonProperty("temp")
    private String temp;
    @JsonProperty("feels_like")
    private String feelsLike;
    @JsonProperty("humidity")
    private String humidity;
    @JsonProperty("min_temp")
    private String minTemp;
    @JsonProperty("max_temp")
    private String maxTemp;
    @JsonProperty("wind_speed")
    private String windSpeed;
    @JsonProperty("wind_degrees")
    private String windDegree;
    @JsonProperty("sunrise")
    private String sunrise;
    @JsonProperty("sunset")
    private String sunset;
}
