package edu.web.countries.models.ninja;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@AllArgsConstructor
@ToString
public class WeatherDTO {
    private String countryName;
    private String capital;
    private String windSpeed;
    private String windDegrees;
    private String temp;
    private String humidity;

    public WeatherDTO(String name, String capital, Weather weather) {
        this.countryName = name;
        this.capital = capital;
        this.windSpeed = weather.getWindSpeed();
        this.windDegrees = weather.getWindDegree();
        this.temp = weather.getTemp();
        this.humidity = weather.getHumidity();
    }

    public Map<String, Object> getHashMap() {
        return Map.of(
                "country_name", this.countryName,
                "capital", this.capital,
                "wind_speed", this.windSpeed,
                "wind_degrees", this.windDegrees,
                "temp", this.temp,
                "humidity", this.humidity
        );
    }
}
