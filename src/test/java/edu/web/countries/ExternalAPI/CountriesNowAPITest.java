package edu.web.countries.ExternalAPI;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class CountriesNowAPITest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void getAllCountries(@Value("${admin.username}") String adminUser, @Value("${admin.password}") String adminPass) throws Exception {
        String username = "mock user";
        String password = "mock pass";
        RegisterTest.register(username, password, this.mockMvc);
        AdminTest.activateUser(username, "true", adminUser, adminPass, this.mockMvc);
        ResultActions response = LoginTest.login(username, password, this.mockMvc);
        String token = LoginTest.getTokenByResponse(response);
        this.mockMvc.
                perform(get("/countries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.countries").exists())
                .andExpect(jsonPath("$.count").exists())
        ;
    }
}
