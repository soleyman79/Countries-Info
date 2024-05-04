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
    private String sexRatio;
    @JsonProperty("surface_area")
    private String surfaceArea;
    @JsonProperty("life_expectancy_male")
    private String lifeExpectancyMale;
    @JsonProperty("unemployment")
    private String unemployment;
    @JsonProperty("imports")
    private String imports;
    @JsonProperty("homicide_rate")
    private String homicideRate;
    @JsonProperty("currency")
    private Currency currency;
    @JsonProperty("iso2")
    private String iso2;
    @JsonProperty("gdp_growth")
    private String gdpGrowth;
    @JsonProperty("employment_services")
    private String employmentServices;
    @JsonProperty("urban_population_growth")
    private String urbanPopulationGrowth;
    @JsonProperty("secondary_school_enrollment_female")
    private String secondarySchoolEnrollmentFemale;
    @JsonProperty("employment_agriculture")
    private String employmentAgriculture;
    @JsonProperty("capital")
    private String capital;
    @JsonProperty("co2_emissions")
    private String co2Emissions;
    @JsonProperty("forested_area")
    private String forestedArea;
    @JsonProperty("tourists")
    private String tourists;
    @JsonProperty("exports")
    private String exports;
    @JsonProperty("life_expectancy_female")
    private String lifeExpectancyFemale;
    @JsonProperty("post_secondary_enrollment_female")
    private String postSecondaryEnrollmentFemale;
    @JsonProperty("post_secondary_enrollment_male")
    private String postSecondaryEnrollmentMale;
    @JsonProperty("primary_school_enrollment_female")
    private String primarySchoolEnrollmentFemale;
    @JsonProperty("infant_mortality")
    private String infantMortality;
    @JsonProperty("secondary_school_enrollment_male")
    private String secondarySchoolEnrollmentMale;
    @JsonProperty("threatened_species")
    private String threatenedSpecies;
    @JsonProperty("population")
    private String population;
    @JsonProperty("urban_population")
    private String urbanPopulation;
    @JsonProperty("employment_industry")
    private String employmentIndustry;
    @JsonProperty("name")
    private String name;
    @JsonProperty("pop_growth")
    private String popGrowth;
    @JsonProperty("region")
    private String region;
    @JsonProperty("pop_density")
    private String popDensity;
    @JsonProperty("internet_users")
    private String internetUsers;
    @JsonProperty("gdp_per_capita")
    private String gdpPerCapita;
    @JsonProperty("fertility")
    private String fertility;
    @JsonProperty("refugees")
    private String refugees;
    @JsonProperty("primary_school_enrollment_male")
    private String primarySchoolEnrollmentMale;
}
