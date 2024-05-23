package edu.web.countries.ExternalAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.web.countries.UserManagement.AdminTest;
import edu.web.countries.UserManagement.LoginTest;
import edu.web.countries.UserManagement.RegisterTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class NinjaAPITest {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final String username = "mock user";
    private final String password = "mock pass";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void getOneCountry(@Value("${admin.username}") String adminUser, @Value("${admin.password}") String adminPass) throws Exception {
        RegisterTest.register(this.username, this.password, this.mockMvc);
        AdminTest.activateUser(this.username, "true", adminUser, adminPass, this.mockMvc);
        ResultActions response = LoginTest.login(this.username, this.password, this.mockMvc);
        String token = LoginTest.getTokenByResponse(response);
        this.mockMvc.
                perform(get("/countries/iran")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Iran, Islamic Republic Of"))
                .andExpect(jsonPath("$.capital").value("Tehran"))
                .andExpect(jsonPath("$.iso2").value("IR"))
                .andExpect(jsonPath("$.population").exists())
                .andExpect(jsonPath("$.pop_growth").exists())
                .andExpect(jsonPath("$.currency").exists())
        ;
    }

    @Test
    @Order(2)
    public void getWithoutPermission() throws Exception {
        this.mockMvc.
                perform(get("/countries")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isUnauthorized())
        ;
    }

    @Test
    @Order(3)
    public void getWeather(@Value("${admin.username}") String adminUser, @Value("${admin.password}") String adminPass) throws Exception {
        RegisterTest.register(this.username, this.password, this.mockMvc);
        AdminTest.activateUser(this.username, "true", adminUser, adminPass, this.mockMvc);
        ResultActions response = LoginTest.login(this.username, this.password, this.mockMvc);
        String token = LoginTest.getTokenByResponse(response);
        this.mockMvc.
                perform(get("/countries/iran/weather")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.country_name").value("Iran, Islamic Republic Of"))
                .andExpect(jsonPath("$.capital").value("Tehran"))
                .andExpect(jsonPath("$.wind_speed").exists())
                .andExpect(jsonPath("$.wind_degrees").exists())
                .andExpect(jsonPath("$.temp").exists())
                .andExpect(jsonPath("$.humidity").exists())
        ;
    }
}
