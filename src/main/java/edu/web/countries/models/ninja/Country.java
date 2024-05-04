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
public class Country {
    @JsonProperty("gdp")
    private String gdp;
    @JsonProperty("sex_ratio")
    private String sex_ratio;
    @JsonProperty("surface_area")
    private String surface_area;
    @JsonProperty("life_expectancy_male")
    private String life_expectancy_male;
    @JsonProperty("unemployment")
    private String unemployment;
    @JsonProperty("imports")
    private String imports;
    @JsonProperty("homicide_rate")
    private String homicide_rate;
    @JsonProperty("currency")
    private Currency currency;
    @JsonProperty("iso2")
    private String iso2;
    @JsonProperty("gdp_growth")
    private String gdp_growth;
    @JsonProperty("employment_services")
    private String employment_services;
    @JsonProperty("urban_population_growth")
    private String urban_population_growth;
    @JsonProperty("secondary_school_enrollment_female")
    private String secondary_school_enrollment_female;
    @JsonProperty("employment_agriculture")
    private String employment_agriculture;
    @JsonProperty("capital")
    private String capital;
    @JsonProperty("co2_emissions")
    private String co2_emissions;
    @JsonProperty("forested_area")
    private String forested_area;
    @JsonProperty("tourists")
    private String tourists;
    @JsonProperty("exports")
    private String exports;
    @JsonProperty("life_expectancy_female")
    private String life_expectancy_female;
    @JsonProperty("post_secondary_enrollment_female")
    private String post_secondary_enrollment_female;
    @JsonProperty("post_secondary_enrollment_male")
    private String post_secondary_enrollment_male;
    @JsonProperty("primary_school_enrollment_female")
    private String primary_school_enrollment_female;
    @JsonProperty("infant_mortality")
    private String infant_mortality;
    @JsonProperty("secondary_school_enrollment_male")
    private String secondary_school_enrollment_male;
    @JsonProperty("threatened_species")
    private String threatened_species;
    @JsonProperty("population")
    private String population;
    @JsonProperty("urban_population")
    private String urban_population;
    @JsonProperty("employment_industry")
    private String employment_industry;
    @JsonProperty("name")
    private String name;
    @JsonProperty("pop_growth")
    private String pop_growth;
    @JsonProperty("region")
    private String region;
    @JsonProperty("pop_density")
    private String pop_density;
    @JsonProperty("internet_users")
    private String internet_users;
    @JsonProperty("gdp_per_capita")
    private String gdp_per_capita;
    @JsonProperty("fertility")
    private String fertility;
    @JsonProperty("refugees")
    private String refugees;
    @JsonProperty("primary_school_enrollment_male")
    private String primary_school_enrollment_male;
}
